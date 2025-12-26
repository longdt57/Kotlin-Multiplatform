package leegroup.module.gituser.domain.usecases

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailLocalUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetGitUserDetailLocalUseCaseTest {

    private lateinit var useCase: GetGitUserDetailLocalUseCase
    private lateinit var mockRepository: MockGitUserDetailRepository

    @BeforeTest
    fun setUp() {
        mockRepository = MockGitUserDetailRepository()
        useCase = GetGitUserDetailLocalUseCase(mockRepository)
    }

    @Test
    fun `test invoke emits data when local data exists`() = runTest {
        // Given
        val login = "testuser"
        val userDetail = GitUserDetailModel(
            id = 1L,
            login = login,
            name = "Test User",
            avatarUrl = "avatar",
            blog = "blog",
            location = "location",
            followers = 100,
            following = 50
        )
        mockRepository.localUser = userDetail

        // When & Then
        useCase(login).test {
            val result = awaitItem()
            assertEquals(login, result.login)
            assertEquals("Test User", result.name)
            assertEquals(100, result.followers)
            awaitComplete()
        }
    }

    @Test
    fun `test invoke does not emit when local data is null`() = runTest {
        // Given
        val login = "testuser"
        mockRepository.localUser = null

        // When & Then
        useCase(login).test {
            awaitComplete()
        }
    }

    @Test
    fun `test invoke calls repository with correct login`() = runTest {
        // Given
        val login = "specificuser"
        mockRepository.localUser = null

        // When
        useCase(login).test {
            awaitComplete()
        }

        // Then
        assertEquals(login, mockRepository.lastLogin)
    }
}

private class MockGitUserDetailRepository : GitUserDetailRepository {
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
