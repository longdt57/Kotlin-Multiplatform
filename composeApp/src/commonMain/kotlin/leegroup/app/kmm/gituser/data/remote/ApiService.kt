package leegroup.app.kmm.gituser.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import leegroup.app.kmm.gituser.data.models.GitUser
import leegroup.app.kmm.gituser.data.models.GitUserDetail

class ApiService {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getGitUser(
        since: Int,
        perPage: Int,
    ): List<GitUser> {
        val url = "${BASE_URL}users?since=$since&per_page=$perPage"
        return httpClient.get(url).body()
    }

    suspend fun getGitUserDetail(
        login: String,
    ): GitUserDetail {
        val url = "${BASE_URL}users/$login"
        return httpClient.get(url).body()
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}