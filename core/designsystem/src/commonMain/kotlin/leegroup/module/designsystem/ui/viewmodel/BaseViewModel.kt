package leegroup.module.designsystem.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import leegroup.module.designsystem.support.extensions.getString
import leegroup.module.designsystem.support.extensions.mapToErrorDialog
import leegroup.module.designsystem.support.extensions.mapToMessage
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.designsystem.ui.models.LoadingState
import leegroup.module.designsystem.ui.models.Message
import org.jetbrains.compose.resources.StringResource

@Suppress("PropertyName", "MemberVisibilityCanBePrivate")
abstract class BaseViewModel : ViewModel() {

    private val _loading: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.None)
    val loading = _loading.asStateFlow()

    protected val _error = MutableStateFlow<ErrorState>(ErrorState.None)
    val error = _error.asStateFlow()

    protected val _message = MutableSharedFlow<Message>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val message = _message.asSharedFlow()

    protected val _navigator = MutableSharedFlow<Any>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val navigator = _navigator.asSharedFlow()

    protected open fun showLoading() {
        _loading.value = LoadingState.Loading()
    }

    protected open fun isLoading(): Boolean {
        return _loading.value is LoadingState.Loading
    }

    protected open fun hideLoading() {
        _loading.value = LoadingState.None
    }

    protected open fun sendErrorState(
        errorState: ErrorState
    ) {
        _error.tryEmit(errorState)
    }

    open fun onErrorConfirmation(errorState: ErrorState) {
        hideError()
    }

    open fun onErrorDismissClick(errorState: ErrorState) {
        hideError()
    }

    protected fun hideError() {
        _error.tryEmit(ErrorState.None)
    }

    protected fun <T> Flow<T>.injectLoading(): Flow<T> = this
        .onStart { showLoading() }
        .onCompletion { hideLoading() }

    protected open suspend fun handleError(e: Throwable) {
        _error.tryEmit(e.mapToErrorDialog())
    }

    protected open suspend fun handleErrorAsMessage(e: Throwable) {
        _message.tryEmit(e.mapToMessage())
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            _message.emit(message)
        }
    }
}

suspend fun BaseViewModel.sendSuccessMessage(
    messageStringId: StringResource? = null,
    alternativeMessage: String? = null
) {
    sendMessage(
        Message.SnackBarMessage.buildSuccess(
            getString(
                messageStringId,
                alternativeMessage
            )
        )
    )
}

suspend fun BaseViewModel.sendErrorMessage(
    messageStringId: StringResource? = null,
    alternativeMessage: String? = null
) {
    sendMessage(
        Message.SnackBarMessage.buildError(
            getString(
                messageStringId,
                alternativeMessage
            )
        )
    )
}
