package leegroup.module.designsystem.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import leegroup.module.designsystem.ui.models.LoadingState

@Composable
fun LoadingView(loading: LoadingState) {
    when (loading) {
        is LoadingState.Loading -> LoadingProgress(loading)
        else -> {}
    }
}

@Preview
@Composable
private fun LoadingViewPreview() {
    leegroup.module.designsystem.theme.ComposeTheme {
        LoadingView(LoadingState.Loading())
    }
}