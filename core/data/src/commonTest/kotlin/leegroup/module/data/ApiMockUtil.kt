package leegroup.module.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.network.model.error.ErrorModel
import leegroup.module.data.network.model.error.SampleCustomErrorModel

internal object ApiMockUtil {

    val sampleErrorModel = ErrorModel(
        code = 101,
        message = "Hello Bro!"
    )

    val sampleCustomErrorModel = SampleCustomErrorModel(
        code = 101,
        message = "Hello Bro!",
        title = "Hello World!"
    )

    fun mockApiError(
        content: String = JsonUtil.encodeToString(sampleErrorModel)
    ): HttpClient {
        val mockEngine = MockEngine { _ ->
            respond(
                content = content.trimIndent(),
                status = HttpStatusCode.BadRequest,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        return HttpClient(mockEngine) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        isLenient = true
                    }
                )
            }
        }
    }

    fun mockCustomApiError() = mockApiError(JsonUtil.encodeToString(sampleCustomErrorModel))
}
