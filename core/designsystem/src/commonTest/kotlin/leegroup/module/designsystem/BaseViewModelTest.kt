package leegroup.module.designsystem

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.test.runTest
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.designsystem.ui.models.LoadingState
import leegroup.module.designsystem.ui.models.Message
import leegroup.module.designsystem.ui.viewmodel.BaseViewModel
import leegroup.module.designsystem.ui.viewmodel.sendErrorMessage
import leegroup.module.designsystem.ui.viewmodel.sendSuccessMessage
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class BaseViewModelTest  {

    private lateinit var mockBaseViewModel: MockBaseViewModel

    @BeforeTest
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

    @Test
    fun `test handleAction SendMessage emits message`() = runTest {
        val msg = "Hello world"
        val testMessage = Message.SnackBarMessage.buildSuccess(message = msg)
        mockBaseViewModel.message.test {
            mockBaseViewModel.handleAction(MockBaseViewModel.Action.SendMessage(testMessage))
            val item = awaitItem()
            assertEquals(msg, (item as Message.SnackBarMessage).message)
        }
    }

    @Test
    fun `test sendErrorState emits error`() = runTest {
        val errorState = ErrorState.Common

        mockBaseViewModel.testSendErrorState(errorState)

        assertEquals(errorState, mockBaseViewModel.error.value)
        assertIs<ErrorState.Common>(mockBaseViewModel.error.value)
    }

    @Test
    fun `test hideError resets error state to None`() = runTest {
        val errorState = ErrorState.Network
        mockBaseViewModel.testSendErrorState(errorState)
        assertEquals(errorState, mockBaseViewModel.error.value)

        mockBaseViewModel.testHideError()

        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
    }

    @Test
    fun `test handleError emits error state`() = runTest {
        val exception = RuntimeException("Test exception")

        mockBaseViewModel.testHandleError(exception)

        // Verify that an error state was set (not None)
        assertTrue(mockBaseViewModel.error.value !is ErrorState.None)
    }

    @Test
    fun `test onErrorConfirmation hides error`() = runTest {
        val errorState = ErrorState.Server
        mockBaseViewModel.testSendErrorState(errorState)
        assertEquals(errorState, mockBaseViewModel.error.value)

        mockBaseViewModel.onErrorConfirmation(errorState)

        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
    }

    @Test
    fun `test onErrorDismissClick hides error`() = runTest {
        val errorState = ErrorState.Common
        mockBaseViewModel.testSendErrorState(errorState)
        assertEquals(errorState, mockBaseViewModel.error.value)

        mockBaseViewModel.onErrorDismissClick(errorState)

        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
    }

    @Test
    fun `test navigator emits navigation events`() = runTest {
        val navigationEvent = "TestDestination"

        mockBaseViewModel.navigator.test {
            mockBaseViewModel.testNavigate(navigationEvent)
            val item = awaitItem()
            assertEquals(navigationEvent, item)
        }
    }

    @Test
    fun `test sendSuccessMessage extension function`() = runTest {
        val successMsg = "Success!"

        mockBaseViewModel.message.test {
            mockBaseViewModel.sendSuccessMessage(alternativeMessage = successMsg)
            val item = awaitItem()
            assertIs<Message.SnackBarMessage>(item)
            assertEquals(successMsg, item.message)
        }
    }

    @Test
    fun `test sendErrorMessage extension function`() = runTest {
        val errorMsg = "Error occurred"

        mockBaseViewModel.message.test {
            mockBaseViewModel.sendErrorMessage(alternativeMessage = errorMsg)
            val item = awaitItem()
            assertIs<Message.SnackBarMessage>(item)
            assertEquals(errorMsg, item.message)
        }
    }

    @Test
    fun `test isLoading returns false when not loading`() = runTest {
        assertEquals(LoadingState.None, mockBaseViewModel.loading.value)
        assertFalse(mockBaseViewModel.assertIsLoading())
    }

    @Test
    fun `test multiple showLoading calls maintain loading state`() = runTest {
        mockBaseViewModel.handleAction(MockBaseViewModel.Action.ShowLoading)
        assertTrue(mockBaseViewModel.assertIsLoading())

        mockBaseViewModel.handleAction(MockBaseViewModel.Action.ShowLoading)
        assertTrue(mockBaseViewModel.assertIsLoading())
    }

    @Test
    fun `test error state transitions`() = runTest {
        // Start with no error
        assertEquals(ErrorState.None, mockBaseViewModel.error.value)

        // Set first error
        val error1 = ErrorState.Network
        mockBaseViewModel.testSendErrorState(error1)
        assertEquals(error1, mockBaseViewModel.error.value)

        // Set second error (should replace first)
        val error2 = ErrorState.Server
        mockBaseViewModel.testSendErrorState(error2)
        assertEquals(error2, mockBaseViewModel.error.value)

        // Hide error
        mockBaseViewModel.testHideError()
        assertEquals(ErrorState.None, mockBaseViewModel.error.value)
    }

    @Test
    fun `test message buffer handles emissions`() = runTest {
        val msg = Message.SnackBarMessage.buildSuccess("Test message")

        mockBaseViewModel.message.test {
            mockBaseViewModel.handleAction(MockBaseViewModel.Action.SendMessage(msg))

            val item = awaitItem()
            assertEquals("Test message", (item as Message.SnackBarMessage).message)
        }
    }

    @Test
    fun `test multiple messages can be sent sequentially`() = runTest {
        mockBaseViewModel.message.test {
            val msg1 = Message.SnackBarMessage.buildSuccess("First")
            mockBaseViewModel.handleAction(MockBaseViewModel.Action.SendMessage(msg1))

            val item1 = awaitItem()
            assertEquals("First", (item1 as Message.SnackBarMessage).message)

            val msg2 = Message.SnackBarMessage.buildSuccess("Second")
            mockBaseViewModel.handleAction(MockBaseViewModel.Action.SendMessage(msg2))

            val item2 = awaitItem()
            assertEquals("Second", (item2 as Message.SnackBarMessage).message)
        }
    }
}

private class MockBaseViewModel : BaseViewModel() {

    fun handleAction(action: Action) {
        when (action) {
            is Action.ShowLoading -> showLoading()
            is Action.HideLoading -> hideLoading()
            is Action.SendMessage -> sendMessage(action.message)
        }
    }

    fun testInjectLoading() = flow {
        delay(100)
        emit(1)
    }.injectLoading()

    fun assertIsLoading() = isLoading()

    fun testSendErrorState(errorState: ErrorState) {
        sendErrorState(errorState)
    }

    fun testHideError() {
        hideError()
    }

    suspend fun testHandleError(e: Throwable) {
        handleError(e)
    }

    fun testNavigate(destination: Any) {
        _navigator.tryEmit(destination)
    }

    sealed interface Action {
        data object ShowLoading : Action
        data object HideLoading : Action
        data class SendMessage(val message: Message) : Action
    }
}
