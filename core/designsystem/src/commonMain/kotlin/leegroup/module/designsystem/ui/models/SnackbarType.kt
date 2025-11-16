package leegroup.module.designsystem.ui.models

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

sealed interface SnackbarType {
    data object Success : SnackbarType
    data object Warning : SnackbarType
    data object Error : SnackbarType
}

data class SnackbarHostStateManager(
    val success: SnackbarHostState,
    val error: SnackbarHostState,
    val warning: SnackbarHostState
) {
    fun getHostState(type: SnackbarType): SnackbarHostState {
        return when (type) {
            SnackbarType.Success -> success
            SnackbarType.Error -> error
            SnackbarType.Warning -> warning
        }
    }
}

val LocalTopSnackbarHostStateManager: ProvidableCompositionLocal<SnackbarHostStateManager> =
    compositionLocalOf {
        error("LocalSnackbarStateManager is not initialized")
    }