package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import gituserkmm.composeapp.generated.resources.Res
import gituserkmm.composeapp.generated.resources.common_retry
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GitUserListEmpty(modifier: Modifier = Modifier, onRefresh: () -> Unit) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Button(onClick = onRefresh) {
            Text(
                text = stringResource(Res.string.common_retry),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun GitUserListEmptyPreview() {
    MaterialTheme {
        GitUserListEmpty(modifier = Modifier.fillMaxSize()) {}
    }
}