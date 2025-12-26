package leegroup.module.gituser.domain.usecases

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import leegroup.module.gituser.domain.models.GitUserModel
import leegroup.module.gituser.domain.repositories.GitUserRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetGitUserUseCaseTest {

    private lateinit var useCase: GetGitUserUseCase
    private lateinit var mockRepository: MockGitUserRepository

    @BeforeTest
    fun setUp() {
        mockRepository = MockGitUserRepository()
        useCase = GetGitUserUseCase(mockRepository)
    }

    @Test
    fun `test invoke returns remote data when local is empty`() = runTest {
        // Given
        val since = 0L
        val perPage = 20
        val remoteUsers = listOf(
            GitUserModel(1L, "user1", "avatar1", "html1"),
            GitUserModel(2L, "user2", "avatar2", "html2")
        )
        mockRepository.localUsers = emptyList()
        mockRepository.remoteUsers = remoteUsers

        // When & Then
        useCase(since, perPage).test {
            val result = awaitItem()
            assertEquals(2, result.size)
            assertEquals("user1", result[0].login)
            assertEquals("user2", result[1].login)
            awaitComplete()
        }
    }

    @Test
    fun `test invoke returns local data when available`() = runTest {
        // Given
        val since = 0L
        val perPage = 20
        val localUsers = listOf(
            GitUserModel(1L, "localuser1", "avatar1", "html1"),
            GitUserModel(2L, "localuser2", "avatar2", "html2")
        )
        mockRepository.localUsers = localUsers

        // When & Then
        useCase(since, perPage).test {
            val result = awaitItem()
            assertEquals(2, result.size)
            assertEquals("localuser1", result[0].login)
            assertEquals("localuser2", result[1].login)
            awaitComplete()
        }
    }

    @Test
    fun `test invoke with pagination parameters`() = runTest {
        // Given
        val since = 100L
        val perPage = 50
        val localUsers = emptyList<GitUserModel>()
        val remoteUsers = listOf(
            GitUserModel(101L, "user101", "avatar101", "html101")
        )
        mockRepository.localUsers = localUsers
        mockRepository.remoteUsers = remoteUsers

        // When & Then
        useCase(since, perPage).test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals(101L, result[0].id)
            assertEquals(since, mockRepository.lastSince)
            assertEquals(perPage, mockRepository.lastPerPage)
            awaitComplete()
        }
    }

    @Test
    fun `test invoke prefers local over remote when local has data`() = runTest {
        // Given
        val localUsers = listOf(GitUserModel(1L, "local", "avatar", "html"))
        val remoteUsers = listOf(GitUserModel(2L, "remote", "avatar", "html"))
        mockRepository.localUsers = localUsers
        mockRepository.remoteUsers = remoteUsers

        // When & Then
        useCase(0L, 20).test {
            val result = awaitItem()
            assertEquals("local", result[0].login)
            assertEquals(false, mockRepository.remoteCalled)
            awaitComplete()
        }
    }
}

private class MockGitUserRepository : GitUserRepository {
    var localUsers: List<GitUserModel> = emptyList()
    var remoteUsers: List<GitUserModel> = emptyList()
    var lastSince: Long = -1
    var lastPerPage: Int = -1
    var remoteCalled: Boolean = false

    override suspend fun getLocal(since: Long, perPage: Int): List<GitUserModel> {
        lastSince = since
        lastPerPage = perPage
        return localUsers
    }

    override suspend fun getRemote(since: Long, perPage: Int): List<GitUserModel> {
        remoteCalled = true
        lastSince = since
        lastPerPage = perPage
        return remoteUsers
    }
}
