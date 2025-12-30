package leegroup.module.gituser.data.repositories

import kotlinx.coroutines.test.runTest
import leegroup.module.gituser.data.local.room.GitUserDao
import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.remote.GitUserApiService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GitUserRepositoryImplTest {

    private lateinit var repository: GitUserRepositoryImpl
    private lateinit var mockApiService: MockGitUserListApiService
    private lateinit var mockUserDao: MockGitUserDao

    @BeforeTest
    fun setUp() {
        mockApiService = MockGitUserListApiService()
        mockUserDao = MockGitUserDao()
        repository = GitUserRepositoryImpl(mockApiService, mockUserDao)
    }

    @Test
    fun `test getRemote fetches from API and saves to local`() = runTest {
        // Given
        val since = 0L
        val perPage = 20
        val apiUsers = listOf(
            GitUser(1L, "user1", "avatar1", "html1"),
            GitUser(2L, "user2", "avatar2", "html2")
        )
        mockApiService.users = apiUsers

        // When
        val result = repository.getRemote(since, perPage)

        // Then
        assertEquals(2, result.size)
        assertEquals("user1", result[0].login)
        assertEquals("user2", result[1].login)
        assertEquals(since, mockApiService.lastSince)
        assertEquals(perPage, mockApiService.lastPerPage)
        assertTrue(mockUserDao.upsertCalled)
        assertEquals(2, mockUserDao.lastUpsertedUsers.size)
    }

    @Test
    fun `test getLocal retrieves from DAO`() = runTest {
        // Given
        val since = 10L
        val perPage = 30
        val localUsers = listOf(
            GitUser(11L, "localuser1", "avatar1", "html1"),
            GitUser(12L, "localuser2", "avatar2", "html2"),
            GitUser(13L, "localuser3", "avatar3", "html3")
        )
        mockUserDao.users = localUsers

        // When
        val result = repository.getLocal(since, perPage)

        // Then
        assertEquals(3, result.size)
        assertEquals("localuser1", result[0].login)
        assertEquals("localuser2", result[1].login)
        assertEquals("localuser3", result[2].login)
        assertEquals(since, mockUserDao.lastSince)
        assertEquals(perPage, mockUserDao.lastPerPage)
    }

    @Test
    fun `test getLocal returns empty list when no data`() = runTest {
        // Given
        mockUserDao.users = emptyList()

        // When
        val result = repository.getLocal(0L, 20)

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun `test getRemote with pagination parameters`() = runTest {
        // Given
        val since = 100L
        val perPage = 50
        val apiUsers = listOf(GitUser(101L, "user101", "avatar", "html"))
        mockApiService.users = apiUsers

        // When
        repository.getRemote(since, perPage)

        // Then
        assertEquals(since, mockApiService.lastSince)
        assertEquals(perPage, mockApiService.lastPerPage)
    }
}

private class MockGitUserListApiService : GitUserApiService {
    var users: List<GitUser> = emptyList()
    var lastSince: Long = -1
    var lastPerPage: Int = -1

    override suspend fun getGitUser(since: Long, perPage: Int): List<GitUser> {
        lastSince = since
        lastPerPage = perPage
        return users
    }

    override suspend fun getGitUserDetail(login: String): leegroup.module.gituser.data.models.GitUserDetailEntity {
        throw NotImplementedError()
    }
}

private class MockGitUserDao : GitUserDao {
    var users: List<GitUser> = emptyList()
    var lastSince: Long = -1
    var lastPerPage: Int = -1
    var upsertCalled: Boolean = false
    var lastUpsertedUsers: List<GitUser> = emptyList()

    override suspend fun getUsers(since: Long, perPage: Int): List<GitUser> {
        lastSince = since
        lastPerPage = perPage
        return users
    }

    override suspend fun upsert(entity: GitUser): Long {
        upsertCalled = true
        lastUpsertedUsers = listOf(entity)
        return entity.id
    }

    override suspend fun upsert(vararg entity: GitUser) {
        upsertCalled = true
        lastUpsertedUsers = entity.toList()
    }

    override suspend fun upsert(entities: Collection<GitUser>) {
        upsertCalled = true
        lastUpsertedUsers = entities.toList()
    }

    override suspend fun delete(entity: GitUser): Int {
        return 1
    }
}
