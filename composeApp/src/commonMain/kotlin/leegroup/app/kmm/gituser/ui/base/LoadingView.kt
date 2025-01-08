package leegroup.app.kmm.gituser.ui.base

import androidx.compose.runtime.Composable

@Composable
fun LoadingView(loading: LoadingState) {
    when (loading) {
        is LoadingState.Loading -> {
            LoadingProgress(loading)
        }

        else -> {}
    }
}