package leegroup.app.kmm.gituser.ui.base

import androidx.annotation.StringRes
import leegroup.app.kmm.gituser.R

sealed interface LoadingState {
    data object None : LoadingState
    data class Loading(@StringRes val messageRes: Int = R.string.loading) : LoadingState
}