package leegroup.module.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gituserkmm.core.designsystem.generated.resources.loading
import leegroup.module.designsystem.support.extensions.stringResourceOrNull
import leegroup.module.designsystem.ui.models.LoadingState
import org.jetbrains.compose.ui.tooling.preview.Preview
import gituserkmm.core.designsystem.generated.resources.Res as R

@Composable
fun LoadingProgress(loading: LoadingState.Loading) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            stringResourceOrNull(loading.messageRes)?.let { text ->
                Text(modifier = Modifier.padding(8.dp), text = text)
            }
        }
    }
}

@Preview
@Composable
private fun LoadingProgressPreview() {
    leegroup.module.designsystem.theme.ComposeTheme {
        LoadingProgress(loading = LoadingState.Loading(messageRes = R.string.loading))
    }
}