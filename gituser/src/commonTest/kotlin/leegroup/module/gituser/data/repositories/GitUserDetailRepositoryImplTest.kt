package leegroup.module.gituser.data.repositories

import kotlinx.coroutines.test.runTest
import leegroup.module.gituser.data.local.room.GitUserDetailDao
import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.models.GitUserDetail
import leegroup.module.gituser.data.remote.GitUserApiService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GitUserDetailRepositoryImplTest {

    private lateinit var repository: GitUserDetailRepositoryImpl
    private lateinit var mockApiService: MockGitUserDetailApiService
    private lateinit var mockUserDao: MockGitUserDetailDao

    @BeforeTest
    fun setUp() {
        mockApiService = MockGitUserDetailApiService()
        mockUserDao = MockGitUserDetailDao()
        repository = GitUserDetailRepositoryImpl(mockApiService, mockUserDao)
    }

    @Test
    fun `test getRemote fetches from API and saves to local`() = runTest {
        // Given
        val login = "testuser"
        val apiUserDetail = GitUserDetail(
            id = 1L,
            login = login,
            name = "Test User",
            avatarUrl = "avatar",
            blog = "blog",
            location = "SF",
            followers = 100,
            following = 50
        )
        mockApiService.userDetail = apiUserDetail

        // When
        val result = repository.getRemote(login)

        // Then
        assertEquals(login, result.login)
        assertEquals("Test User", result.name)
        assertEquals(100, result.followers)
        assertEquals(50, result.following)
        assertEquals(login, mockApiService.lastLogin)
        assertTrue(mockUserDao.upsertCalled)
        assertEquals(login, mockUserDao.lastUpsertedUser?.login)
    }

    @Test
    fun `test getLocal retrieves from DAO`() = runTest {
        // Given
        val login = "localuser"
        val localUserDetail = GitUserDetail(
            id = 2L,
            login = login,
            name = "Local User",
            avatarUrl = "avatar",
            blog = "blog",
            location = "NYC",
            followers = 200,
            following = 100
        )
        mockUserDao.userDetail = localUserDetail

        // When
        val result = repository.getLocal(login)

        // Then
        assertEquals(login, result?.login)
        assertEquals("Local User", result?.name)
        assertEquals(200, result?.followers)
        assertEquals(100, result?.following)
        assertEquals(login, mockUserDao.lastLogin)
    }

    @Test
    fun `test getLocal returns null when no data`() = runTest {
        // Given
        val login = "nonexistent"
        mockUserDao.userDetail = null

        // When
        val result = repository.getLocal(login)

        // Then
        assertNull(result)
        assertEquals(login, mockUserDao.lastLogin)
    }

    @Test
    fun `test getRemote maps data correctly`() = runTest {
        // Given
        val login = "mapper"
        val apiUserDetail = GitUserDetail(
            id = 3L,
            login = login,
            name = null,
            avatarUrl = null,
            blog = null,
            location = null,
            followers = null,
            following = null
        )
        mockApiService.userDetail = apiUserDetail

        // When
        val result = repository.getRemote(login)

        // Then
        assertEquals(3L, result.id)
        assertEquals(login, result.login)
        assertEquals(null, result.name)
        assertEquals(0, result.followers)
        assertEquals(0, result.following)
    }
}

private class MockGitUserDetailApiService : GitUserApiService {
    var userDetail: GitUserDetail? = null
    var lastLogin: String = ""

    override suspend fun getGitUser(since: Long, perPage: Int): List<GitUser> {
        throw NotImplementedError()
    }

    override suspend fun getGitUserDetail(login: String): GitUserDetail {
        lastLogin = login
        return userDetail ?: throw IllegalStateException("No user detail set")
    }
}

private class MockGitUserDetailDao : GitUserDetailDao {
    var userDetail: GitUserDetail? = null
    var lastLogin: String = ""
    var upsertCalled: Boolean = false
    var lastUpsertedUser: GitUserDetail? = null

    override suspend fun getUserDetail(login: String): GitUserDetail? {
        lastLogin = login
        return userDetail
    }

    override suspend fun upsert(entity: GitUserDetail): Long {
        upsertCalled = true
        lastUpsertedUser = entity
        return entity.id
    }

    override suspend fun upsert(vararg entity: GitUserDetail) {
        upsertCalled = true
        lastUpsertedUser = entity.firstOrNull()
    }

    override suspend fun upsert(entities: Collection<GitUserDetail>) {
        upsertCalled = true
        lastUpsertedUser = entities.firstOrNull()
    }

    override suspend fun delete(entity: GitUserDetail): Int {
        return 1
    }
}
