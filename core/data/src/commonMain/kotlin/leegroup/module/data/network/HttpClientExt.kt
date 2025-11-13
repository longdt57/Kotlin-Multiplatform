package leegroup.module.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

suspend inline fun <reified Request, reified Response> HttpClient.post(
    url: String,
    request: Request,
) = post(url) {
    setBody(request)
}.body<Response>()