package leegroup.module.data.di

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.getCorePlatform

object KtorHttpClientProvider {

    fun provideHttpClient(
        isLoggingEnable: Boolean,
        configs: (HttpClientConfig<*>) -> Unit,
        block: DefaultRequest.DefaultRequestBuilder.() -> Unit
    ): HttpClient {
        return getCorePlatform().createHttpClient {
            configs(this)
            install(ContentNegotiation) {
                json(JsonUtil.json)
            }

            if (isLoggingEnable) {
                install(Logging) {
                    level = LogLevel.ALL // TODO Các mức: NONE, HEADERS, BODY, ALL
                }
            }

            defaultRequest {
                block()
                header(HttpHeaders.Accept, "application/json")
            }
        }
    }
}
