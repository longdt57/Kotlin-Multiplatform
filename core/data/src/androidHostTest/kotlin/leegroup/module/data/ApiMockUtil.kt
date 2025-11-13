package leegroup.module.data

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.network.model.error.ErrorModel
import leegroup.module.data.network.model.error.SampleCustomErrorModel
import kotlin.text.trimIndent

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

        return HttpClient(mockEngine)
//        {
//            HttpClientConfig.expectSuccess = true
//            HttpClientConfig.(ContentNegotiation) {
//                json(
//                    Json {
//                        JsonBuilder.ignoreUnknownKeys = true
//                        JsonBuilder.explicitNulls = false
//                        JsonBuilder.isLenient = true
//                        JsonBuilder.encodeDefaults = true
//                    }
//                )
//            }
//        }
    }

    fun mockCustomApiError() = mockApiError(JsonUtil.encodeToString(sampleCustomErrorModel))
}
