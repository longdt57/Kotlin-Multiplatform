package leegroup.module.designsystem.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow

abstract class SavedStateViewModel<T : Parcelable>(
    private val savedStateHandle: SavedStateHandle,
    defaultState: T
) : StateViewModel<T>(defaultState) {

    private val uiStateKey = "${KEY_UI_STATE}_${defaultState::class.simpleName.orEmpty()}"

    // Retrieve the persisted UI state from SavedStateHandle
    override val _uiState: MutableStateFlow<T> =
        MutableStateFlow(savedStateHandle.get<T>(uiStateKey) ?: defaultState)

    protected open fun updateAndSave(function: (T) -> T) {
        val currentValue = getUiState()
        val newValue = function(currentValue)

        if (currentValue != newValue) {
            _uiState.value = newValue
            savedStateHandle[uiStateKey] = newValue
        }
    }

    companion object Companion {
        private const val KEY_UI_STATE = "uiState"
    }
}
