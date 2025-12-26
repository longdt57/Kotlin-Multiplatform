package leegroup.module.gituser.ui.screens.gituser

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.gituser.domain.models.GitUserModel
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.test.BaseKmpUnitTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GitUserListViewModelTest : BaseKmpUnitTest() {

    private lateinit var viewModel: GitUserListViewModel
    private lateinit var mockRepository: MockGitUserRepository
    private lateinit var useCase: GetGitUserUseCase

    @BeforeTest
    override fun setUp() {
        super.setUp()
        mockRepository = MockGitUserRepository()
        useCase = GetGitUserUseCase(mockRepository)
        viewModel = GitUserListViewModel(testDispatchersProvider, useCase)
    }

    @AfterTest
    override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun `test initial state has empty users list`() = runTest(testDispatcher) {
        // Then
        viewModel.uiModel.test {
            val state = awaitItem()
            assertTrue(state.users.isEmpty())
        }
    }

    @Test
    fun `test LoadIfEmpty action loads data when list is empty`() = runTest(testDispatcher) {
        // Given
        val users = listOf(
            GitUserModel(1L, "user1", "avatar1", "html1"),
            GitUserModel(2L, "user2", "avatar2", "html2")
        )
        mockRepository.remoteUsers = users

        // When
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
        advanceUntilIdle()

        // Then
        viewModel.uiModel.test {
            val state = awaitItem()
            assertEquals(2, state.users.size)
            assertEquals("user1", state.users[0].login)
            assertEquals("user2", state.users[1].login)
        }
    }

    @Test
    fun `test LoadIfEmpty action does not load when list has data`() = runTest(testDispatcher) {
        // Given - First load some data
        mockRepository.remoteUsers = listOf(GitUserModel(1L, "existing", "avatar", "html"))
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
        advanceUntilIdle()

        // Reset mock to track new calls
        mockRepository.remoteCalls = 0
        mockRepository.remoteUsers = listOf(GitUserModel(2L, "new", "avatar", "html"))

        // When - Try to load again
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
        advanceUntilIdle()

        // Then - Should not have called remote again
        assertEquals(0, mockRepository.remoteCalls)
        viewModel.uiModel.test {
            val state = awaitItem()
            assertEquals(1, state.users.size)
            assertEquals("existing", state.users[0].login)
        }
    }

    @Test
    fun `test LoadMore action appends new users`() = runTest(testDispatcher) {
        // Given - First load
        mockRepository.remoteUsers = listOf(GitUserModel(1L, "user1", "avatar1", "html1"))
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // When - Load more
        mockRepository.remoteUsers = listOf(GitUserModel(2L, "user2", "avatar2", "html2"))
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // Then
        viewModel.uiModel.test {
            val state = awaitItem()
            assertEquals(2, state.users.size)
            assertEquals("user1", state.users[0].login)
            assertEquals("user2", state.users[1].login)
        }
    }

    @Test
    fun `test LoadMore removes duplicate users by id`() = runTest(testDispatcher) {
        // Given - First load
        mockRepository.remoteUsers = listOf(
            GitUserModel(1L, "user1", "avatar1", "html1"),
            GitUserModel(2L, "user2", "avatar2", "html2")
        )
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // When - Load more with duplicate
        mockRepository.remoteUsers = listOf(
            GitUserModel(2L, "user2", "avatar2", "html2"),
            GitUserModel(3L, "user3", "avatar3", "html3")
        )
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // Then - Should have 3 unique users
        viewModel.uiModel.test {
            val state = awaitItem()
            assertEquals(3, state.users.size)
            assertEquals(1L, state.users[0].id)
            assertEquals(2L, state.users[1].id)
            assertEquals(3L, state.users[2].id)
        }
    }

    @Test
    fun `test LoadMore uses correct since parameter`() = runTest(testDispatcher) {
        // Given - First load
        mockRepository.remoteUsers = listOf(
            GitUserModel(10L, "user10", "avatar", "html"),
            GitUserModel(20L, "user20", "avatar", "html")
        )
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // When - Load more
        mockRepository.remoteUsers = emptyList()
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // Then - Should use the last user's id as since
        assertEquals(20L, mockRepository.lastSince)
    }

    @Test
    fun `test LoadMore uses default since of 0 when empty`() = runTest(testDispatcher) {
        // When
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // Then
        assertEquals(0L, mockRepository.lastSince)
    }

    @Test
    fun `test LoadMore uses correct perPage parameter`() = runTest(testDispatcher) {
        // When
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()

        // Then
        assertEquals(GitUserListViewModel.PER_PAGE, mockRepository.lastPerPage)
    }

    @Test
    fun `test loading state during data fetch`() = runTest(testDispatcher) {
        // Given
        mockRepository.remoteUsers = listOf(GitUserModel(1L, "user", "avatar", "html"))

        // When
        viewModel.loading.test {
            skipItems(1) // Skip initial state
            viewModel.handleAction(GitUserListAction.LoadMore)

            // Then - Should be loading
            val loadingState = awaitItem()
            assertTrue(loadingState is leegroup.module.designsystem.ui.models.LoadingState.Loading)

            advanceUntilIdle()

            // Should stop loading
            val finalState = awaitItem()
            assertFalse(finalState is leegroup.module.designsystem.ui.models.LoadingState.Loading)
        }
    }

    @Test
    fun `test onErrorConfirmation with Network error retries load`() = runTest(testDispatcher) {
        // Given
        mockRepository.remoteUsers = listOf(GitUserModel(1L, "user", "avatar", "html"))

        // When
        viewModel.onErrorConfirmation(ErrorState.Network)
        advanceUntilIdle()

        // Then - Should have called remote to retry
        assertTrue(mockRepository.remoteCalls > 0)
    }

    @Test
    fun `test onErrorConfirmation with Api error retries load`() = runTest(testDispatcher) {
        // Given
        mockRepository.remoteUsers = listOf(GitUserModel(1L, "user", "avatar", "html"))

        // When
        viewModel.onErrorConfirmation(ErrorState.Api())
        advanceUntilIdle()

        // Then - Should have called remote to retry
        assertTrue(mockRepository.remoteCalls > 0)
    }

    @Test
    fun `test onErrorConfirmation with other error does not retry`() = runTest(testDispatcher) {
        // When
        viewModel.onErrorConfirmation(ErrorState.Common)
        advanceUntilIdle()

        // Then - Should not have called remote
        assertEquals(0, mockRepository.remoteCalls)
    }
}

private class MockGitUserRepository :
    leegroup.module.gituser.domain.repositories.GitUserRepository {
    var localUsers: List<GitUserModel> = emptyList()
    var remoteUsers: List<GitUserModel> = emptyList()
    var lastSince: Long = -1
    var lastPerPage: Int = -1
    var remoteCalls: Int = 0

    override suspend fun getLocal(since: Long, perPage: Int): List<GitUserModel> {
        lastSince = since
        lastPerPage = perPage
        return localUsers
    }

    override suspend fun getRemote(since: Long, perPage: Int): List<GitUserModel> {
        remoteCalls++
        lastSince = since
        lastPerPage = perPage
        return remoteUsers
    }
}
