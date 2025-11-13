package leegroup.module.data

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import kotlinx.coroutines.test.runTest
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.network.ErrorMapper.mapApiCustomError
import leegroup.module.data.network.ErrorMapper.mapApiError
import leegroup.module.data.network.model.error.ErrorModel
import leegroup.module.data.network.model.error.SampleCustomErrorModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ErrorMappingTest {

    @Test
    fun `test 400 error with code`() = runTest {
        val expectedError = ErrorModel(
            code = 101,
            message = "Hello Bro!"
        )

        val client = ApiMockUtil.mockApiError(JsonUtil.encodeToString(expectedError))

        try {
            client.get("https://fake.api/test")
        } catch (e: ClientRequestException) {
            val error = e.mapApiError()

            assertEquals(expectedError, error)
        }
    }

    @Test
    fun `test 400 custom error with code`() = runTest {
        val customError = SampleCustomErrorModel(
            code = 101,
            message = "Hello Bro!",
            title = "Hello World!"
        )

        val client = ApiMockUtil.mockApiError(JsonUtil.encodeToString(customError))

        try {
            client.get("https://fake.api/test")
        } catch (e: ClientRequestException) {
            val error = e.mapApiCustomError<SampleCustomErrorModel>()

            assertEquals(customError, error)
        }
    }
}