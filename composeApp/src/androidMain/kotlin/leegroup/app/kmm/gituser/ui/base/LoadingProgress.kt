package leegroup.app.kmm.gituser.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import leegroup.app.kmm.gituser.R

@Composable
fun LoadingProgress(loading: LoadingState.Loading) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            loading.messageRes.takeIf { it > 0 }?.let {
                val text = stringResource(id = it)
                Text(modifier = Modifier.padding(8.dp), text = text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingProgressPreview() {
    MaterialTheme {
        LoadingProgress(loading = LoadingState.Loading(messageRes = R.string.loading))
    }
}