package leegroup.module.gituser.domain.usecases

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailRemoteUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetGitUserDetailRemoteUseCaseTest {

    private lateinit var useCase: GetGitUserDetailRemoteUseCase
    private lateinit var mockRepository: MockGitUserDetailRemoteRepository

    @BeforeTest
    fun setUp() {
        mockRepository = MockGitUserDetailRemoteRepository()
        useCase = GetGitUserDetailRemoteUseCase(mockRepository)
    }

    @Test
    fun `test invoke emits remote data`() = runTest {
        // Given
        val login = "testuser"
        val userDetail = GitUserDetailModel(
            id = 1L,
            login = login,
            name = "Test User",
            avatarUrl = "avatar",
            blog = "blog",
            location = "San Francisco",
            followers = 200,
            following = 100
        )
        mockRepository.remoteUser = userDetail

        // When & Then
        useCase(login).test {
            val result = awaitItem()
            assertEquals(login, result.login)
            assertEquals("Test User", result.name)
            assertEquals("San Francisco", result.location)
            assertEquals(200, result.followers)
            assertEquals(100, result.following)
            awaitComplete()
        }
    }

    @Test
    fun `test invoke calls repository with correct login`() = runTest {
        // Given
        val login = "specificuser"
        val userDetail = GitUserDetailModel(
            id = 1L,
            login = login,
            name = "Name",
            avatarUrl = null,
            blog = null,
            location = null,
            followers = 0,
            following = 0
        )
        mockRepository.remoteUser = userDetail

        // When
        useCase(login).test {
            awaitItem()
            awaitComplete()
        }

        // Then
        assertEquals(login, mockRepository.lastLogin)
    }

    @Test
    fun `test invoke handles user with minimal data`() = runTest {
        // Given
        val login = "minimaluser"
        val userDetail = GitUserDetailModel(
            id = 999L,
            login = login,
            name = null,
            avatarUrl = null,
            blog = null,
            location = null,
            followers = 0,
            following = 0
        )
        mockRepository.remoteUser = userDetail

        // When & Then
        useCase(login).test {
            val result = awaitItem()
            assertEquals(999L, result.id)
            assertEquals(login, result.login)
            assertEquals(null, result.name)
            assertEquals(0, result.followers)
            awaitComplete()
        }
    }
}

private class MockGitUserDetailRemoteRepository : GitUserDetailRepository {
    var localUser: GitUserDetailModel? = null
    var remoteUser: GitUserDetailModel? = null
    var lastLogin: String = ""

    override suspend fun getLocal(login: String): GitUserDetailModel? {
        lastLogin = login
        return localUser
    }

    override suspend fun getRemote(login: String): GitUserDetailModel {
        lastLogin = login
        return remoteUser ?: throw IllegalStateException("No remote user set")
    }
}
