package leegroup.module.designsystem

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.test.runTest
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.designsystem.ui.models.LoadingState
import leegroup.module.designsystem.ui.viewmodel.BaseViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MockBaseViewModelTest {

    private lateinit var mockBaseViewModel: MockBaseViewModel

    @Before
    fun setUp() {
        mockBaseViewModel = MockBaseViewModel()
    }

    @Test
    fun `test handleAction ShowLoading triggers showLoading`() = runTest {
        // Call the ShowLoading action
        mockBaseViewModel.handleAction(MockBaseViewModel.Action.ShowLoading)

        // Verify that loading state is set to Loading
        assertTrue(mockBaseViewModel.assertIsLoading())
    }

    @Test
    fun `test inject loading`() = runTest {
        // Call the ShowLoading action
        mockBaseViewModel.testInjectLoading()
            .onCompletion {
                assertEquals(LoadingState.None, mockBaseViewModel.loading.value)
            }
            .collect {
                assertTrue(mockBaseViewModel.loading.value is LoadingState.Loading)
                assertEquals(1, it)
            }

    }

    @Test
    fun `test handleAction HideLoading triggers hideLoading`() = runTest {
        // First, set loading state to show
        mockBaseViewModel.handleAction(MockBaseViewModel.Action.ShowLoading)
        assertTrue(mockBaseViewModel.loading.value is LoadingState.Loading)

        // Now, hide loading
        mockBaseViewModel.handleAction(MockBaseViewModel.Action.HideLoading)

        // Verify that the loading state is reset to None
        assertEquals(LoadingState.None, mockBaseViewModel.loading.value)
    }

//    @Test
//    fun `test handleAction HandleError triggers correct error state`() = runTest {
//        val exception = MockUtil.noConnectivityException
//
//        // Trigger HandleError action
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.HandleError(exception))
//
//        // Assert that error state is set to Network error
//        assertTrue(mockBaseViewModel.error.value is ErrorState.Network)
//    }
//
//    @Test
//    fun `test handleAction HideError triggers hideError`() = runTest {
//        // Trigger an error to set an error state
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.HandleError(MockUtil.serverException))
//
//        // Assert that an error state is present
//        assertTrue(mockBaseViewModel.error.value is ErrorState.Server)
//
//        // Now, hide the error
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.HideError)
//
//        // Assert that error state is reset to None
//        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
//    }

//    @Test
//    fun `test handle api error`() = runTest {
//        // Trigger an error state
//        mockBaseViewModel.handleAction(
//            MockBaseViewModel.Action.HandleError(MockUtil.apiError)
//        )
//
//        // Assert that an error state is set
//        assertTrue(mockBaseViewModel.error.value is ErrorState.Api)
//    }

//    @Test
//    fun `test handleAction OnErrorDismissClick triggers hideError`() = runTest {
//        // Trigger an error state
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.HandleError(MockUtil.apiError))
//
//        // Assert that an error state is set
//        assertTrue(mockBaseViewModel.error.value is ErrorState.Api)
//
//        // Now, simulate error dismissal
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.OnErrorDismissClick)
//
//        // Assert that error state is reset to None
//        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
//    }

//    @Test
//    fun `test handleAction OnErrorConfirmation triggers hideError`() = runTest {
//        // Trigger an error state
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.HandleError(MockUtil.serverException))
//
//        // Assert that an error state is set
//        assertTrue(mockBaseViewModel.error.value is ErrorState.Server)
//
//        // Now, simulate error confirmation
//        mockBaseViewModel.handleAction(MockBaseViewModel.Action.OnErrorConfirmation)
//
//        // Assert that error state is reset to None
//        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
//    }
}

private class MockBaseViewModel : BaseViewModel() {

    suspend fun handleAction(action: Action) {
        when (action) {
            is Action.ShowLoading -> showLoading()
            is Action.HideLoading -> hideLoading()
            is Action.HandleError -> handleError(action.throwable)
            is Action.HideError -> hideError()
            is Action.OnErrorDismissClick -> onErrorDismissClick(ErrorState.None)
            is Action.OnErrorConfirmation -> onErrorConfirmation(ErrorState.None)
        }
    }

    fun testInjectLoading() = flow {
        delay(100)
        emit(1)
    }.injectLoading()

    fun assertIsLoading() = isLoading()

    sealed interface Action {
        data object ShowLoading : Action
        data object HideLoading : Action
        data class HandleError(val throwable: Throwable) : Action
        data object HideError : Action
        data object OnErrorDismissClick : Action
        data object OnErrorConfirmation : Action
    }
}
