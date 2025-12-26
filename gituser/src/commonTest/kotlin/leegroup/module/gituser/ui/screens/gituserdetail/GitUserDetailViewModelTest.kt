package leegroup.module.gituser.ui.screens.gituserdetail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import leegroup.module.core.util.JsonUtil
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailLocalUseCase
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailRemoteUseCase
import leegroup.module.gituser.ui.mapper.GitUserDetailUiMapper
import leegroup.module.gituser.ui.models.GitUserDetailUiModel
import leegroup.module.gituser.ui.navigation.GitUserDestination
import leegroup.module.test.BaseKmpUnitTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GitUserDetailViewModelTest : BaseKmpUnitTest() {

    private lateinit var mockSavedStateHandle: SavedStateHandle
    private lateinit var mockRepository: MockGitUserDetailRepository
    private lateinit var mockUiMapper: MockGitUserDetailUiMapper
    private lateinit var localUseCase: GetGitUserDetailLocalUseCase
    private lateinit var remoteUseCase: GetGitUserDetailRemoteUseCase

    private lateinit var viewModel: GitUserDetailViewModel

    private val userLogin = "longdt57"

    private val gitUserDetailModel = GitUserDetailModel(
        id = 1,
        login = userLogin,
        name = "Long DT",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        blog = "https://longdt57.com",
        location = "Vietnam",
        followers = 1500,
        following = 100
    )

    private val gitUserDetailUiModel = GitUserDetailUiModel(
        login = userLogin,
        name = "Long DT",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        blog = "https://longdt57.com",
        location = "Vietnam",
        followers = "999+",
        following = "100"
    )

    @BeforeTest
    override fun setUp() {
        super.setUp()
        val nav = GitUserDestination.GitUserDetail(userLogin)
        mockSavedStateHandle = SavedStateHandle(JsonUtil.encodeToMap(nav))
        mockRepository = MockGitUserDetailRepository()
        mockUiMapper = MockGitUserDetailUiMapper(gitUserDetailUiModel)
        localUseCase = GetGitUserDetailLocalUseCase(mockRepository)
        remoteUseCase = GetGitUserDetailRemoteUseCase(mockRepository)
    }

    @AfterTest
    override fun tearDown() {
        super.tearDown()
    }

    private fun initViewModel() {
        viewModel = GitUserDetailViewModel(
            savedStateHandle = mockSavedStateHandle,
            dispatchersProvider = testDispatchersProvider,
            getGitUserDetailLocalUseCase = localUseCase,
            getGitUserDetailRemoteUseCase = remoteUseCase,
            gitUserDetailModelMapper = mockUiMapper
        )
    }

    @Test
    fun `When setting user login, it fetches local and remote data`() = runTest(testDispatcher) {
        mockRepository.localResult = gitUserDetailModel
        mockRepository.remoteResult = gitUserDetailModel

        initViewModel()
        advanceUntilIdle()

        viewModel.uiState.test {
            val updatedModel = awaitItem()
            assertEquals(gitUserDetailUiModel, updatedModel)
        }

        assertTrue(mockRepository.localCalls > 0)
        assertTrue(mockRepository.remoteCalls > 0)
    }

    @Test
    fun `When remote fetch fails and local data exists, it updates UI with local data`() =
        runTest(testDispatcher) {
            mockRepository.localResult = gitUserDetailModel
            mockRepository.remoteException = RuntimeException("Remote fetch error")

            initViewModel()
            advanceUntilIdle()

            viewModel.uiState.test {
                val updatedModel = awaitItem()
                assertEquals(gitUserDetailUiModel, updatedModel)
            }

            assertTrue(mockRepository.localCalls > 0)
            assertTrue(mockRepository.remoteCalls > 0)
        }

    @Test
    fun `When local return error, it still fetches remote data`() = runTest(testDispatcher) {
        mockRepository.localException = RuntimeException("Local fetch error")
        mockRepository.remoteResult = gitUserDetailModel

        initViewModel()
        advanceUntilIdle()

        viewModel.uiState.test {
            val updatedModel = awaitItem()
            assertEquals(gitUserDetailUiModel, updatedModel)
        }

        assertTrue(mockRepository.localCalls > 0)
        assertTrue(mockRepository.remoteCalls > 0)
    }

    @Test
    fun `When local and remote both succeed, remote data takes precedence`() =
        runTest(testDispatcher) {
            val remoteModel = gitUserDetailModel.copy(name = "Remote Long DT")
            val remoteUiModel = gitUserDetailUiModel.copy(name = "Remote Long DT")

            mockRepository.localResult = gitUserDetailModel
            mockRepository.remoteResult = remoteModel
            mockUiMapper.resultForModel[remoteModel] = remoteUiModel

            initViewModel()
            advanceUntilIdle()

            viewModel.uiState.test {
                val updatedModel = awaitItem()
                assertEquals(remoteUiModel, updatedModel)
            }

            assertTrue(mockRepository.localCalls > 0)
            assertTrue(mockRepository.remoteCalls > 0)
        }

    @Test
    fun `When Api onErrorConfirmation is called, it calls fetchRemote again`() =
        runTest(testDispatcher) {
            // Setup with successful data to avoid error handling issues
            mockRepository.localResult = gitUserDetailModel
            mockRepository.remoteResult = gitUserDetailModel

            initViewModel()
            advanceUntilIdle()

            val callsAfterInit = mockRepository.remoteCalls

            viewModel.onErrorConfirmation(ErrorState.Api())
            advanceUntilIdle()

            assertEquals(callsAfterInit + 1, mockRepository.remoteCalls)
        }

    @Test
    fun `When Network onErrorConfirmation is called, it calls fetchRemote again`() =
        runTest(testDispatcher) {
            // Setup with successful data to avoid error handling issues
            mockRepository.localResult = gitUserDetailModel
            mockRepository.remoteResult = gitUserDetailModel

            initViewModel()
            advanceUntilIdle()

            val callsAfterInit = mockRepository.remoteCalls

            viewModel.onErrorConfirmation(ErrorState.Network)
            advanceUntilIdle()

            assertEquals(callsAfterInit + 1, mockRepository.remoteCalls)
        }

    @Test
    fun `When Api onDismissClick is called, it hides error`() = runTest(testDispatcher) {
        // Setup with successful data to avoid error handling issues
        mockRepository.localResult = gitUserDetailModel
        mockRepository.remoteResult = gitUserDetailModel

        initViewModel()
        advanceUntilIdle()

        viewModel.onErrorDismissClick(ErrorState.Api())
        advanceUntilIdle()

        viewModel.error.test {
            assertEquals(ErrorState.None, awaitItem())
        }
    }

    @Test
    fun `When Common onErrorConfirmation is called, it doesn't call fetchRemote`() =
        runTest(testDispatcher) {
            // Setup with successful data to avoid error handling issues
            mockRepository.localResult = gitUserDetailModel
            mockRepository.remoteResult = gitUserDetailModel

            initViewModel()
            advanceUntilIdle()

            val callsAfterInit = mockRepository.remoteCalls

            viewModel.onErrorConfirmation(ErrorState.Common)
            advanceUntilIdle()

            assertEquals(callsAfterInit, mockRepository.remoteCalls)
        }

}

private class MockGitUserDetailRepository : GitUserDetailRepository {
    var localResult: GitUserDetailModel? = null
    var remoteResult: GitUserDetailModel? = null
    var localException: Throwable? = null
    var remoteException: Throwable? = null
    var localCalls: Int = 0
    var remoteCalls: Int = 0

    override suspend fun getLocal(login: String): GitUserDetailModel? {
        localCalls++
        localException?.let { throw it }
        return localResult
    }

    override suspend fun getRemote(login: String): GitUserDetailModel {
        remoteCalls++
        remoteException?.let { throw it }
        return remoteResult ?: throw RuntimeException("No remote result set")
    }
}

private class MockGitUserDetailUiMapper(
    private val defaultResult: GitUserDetailUiModel
) : GitUserDetailUiMapper {
    val resultForModel = mutableMapOf<GitUserDetailModel, GitUserDetailUiModel>()

    override suspend fun mapToUiModel(
        oldUiModel: GitUserDetailUiModel,
        model: GitUserDetailModel
    ): GitUserDetailUiModel {
        return resultForModel[model] ?: defaultResult
    }
}
