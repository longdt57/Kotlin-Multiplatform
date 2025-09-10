package leegroup.module.designsystem.ui.models

import gituserkmm.core.designsystem.generated.resources.loading
import org.jetbrains.compose.resources.StringResource
import gituserkmm.core.designsystem.generated.resources.Res as R

sealed interface LoadingState {
    data object None : LoadingState
    data class Loading(val messageRes: StringResource? = R.string.loading) : LoadingState
}