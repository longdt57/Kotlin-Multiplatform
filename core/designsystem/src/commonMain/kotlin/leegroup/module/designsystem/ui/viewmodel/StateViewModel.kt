package leegroup.module.designsystem.ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Suppress("PropertyName", "MemberVisibilityCanBePrivate")
abstract class StateViewModel<T>(
    initialUiState: T
) : BaseViewModel() {

    protected open val _uiState: MutableStateFlow<T> = MutableStateFlow(initialUiState)
    val uiState: StateFlow<T> get() = _uiState

    protected open fun update(function: (T) -> T) {
        _uiState.update(function)
    }

    protected fun getUiState() = _uiState.value
}