package leegroup.module.gituser.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.models.GitUserDetailEntity

internal class GitUserApiServiceImpl(
    private val httpClient: HttpClient
) : GitUserApiService {
    override suspend fun getGitUser(
        since: Long,
        perPage: Int
    ): List<GitUser> {
        return httpClient.get(GET_USER) {
            url {
                parameters.append("since", since.toString())
                parameters.append("per_page", perPage.toString())
            }
        }.body()
    }

    override suspend fun getGitUserDetail(login: String): GitUserDetailEntity {
        return httpClient.get(GET_USER_DETAIL + login).body()
    }

    companion object {
        private const val GET_USER = "users"
        private const val GET_USER_DETAIL = "users/"
    }
}