package leegroup.module.data

import app.cash.turbine.test
import io.ktor.client.request.get
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.network.ResponseMapper.asCustomResult
import leegroup.module.data.network.ResponseMapper.asResult
import leegroup.module.data.network.ResponseMapper.flowTransform
import leegroup.module.data.network.model.error.ErrorModel
import leegroup.module.data.network.model.error.SampleCustomErrorModel
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ResponseMappingTest {

    @Test
    fun `asResult emits Success on normal flow`() = runTest {
        val flow = flowOf("Data").asResult()

        flow.test {
            val item = awaitItem()
            assert(item.isSuccess)
            assertEquals("Data", item.getOrThrow())
            awaitComplete()
        }
    }

    @Test
    fun `asResult emits Error on exception`() = runTest {
        val error = RuntimeException("Boom")
        val flow = flowTransform<String> { throw error }.asResult()

        flow.test {
            val item = expectMostRecentItem()
            assert(item.isFailure)
            assertEquals(error, item.exceptionOrNull())
            awaitComplete()
        }
    }

    @Test
    fun `test 400 error with code`() = runTest {
        val expectedError = ErrorModel(
            code = 101,
            message = "Hello Bro!"
        )

        val client =
            ApiMockUtil.mockApiError(JsonUtil.encodeToString(expectedError)) // use kotlinx.serialization

        flowTransform<Unit> {
            client.get("https://fake.api/test")
        }.asResult().test {
            val item = awaitItem()
            assert(item.isFailure)
            assertEquals(expectedError, item.exceptionOrNull())
            awaitComplete()
        }
    }

    @Test
    fun `test 400 error with custom code`() = runTest {
        val expectedError = SampleCustomErrorModel(
            code = 101,
            message = "Hello Bro!",
            title = "Hello World!"
        )

        val client =
            ApiMockUtil.mockApiError(JsonUtil.encodeToString(expectedError)) // use kotlinx.serialization

        flowTransform<Unit> {
            client.get("https://fake.api/test")
        }.asCustomResult<Unit, SampleCustomErrorModel>().test {
            val item = awaitItem()
            assert(item.isFailure)
            assertEquals(expectedError, item.exceptionOrNull())
            awaitComplete()
        }
    }
}