package leegroup.app.kmm.gituser.ui.base


sealed interface LoadingState {
    data object None : LoadingState
    data class Loading(val message: String? = null) : LoadingState
}