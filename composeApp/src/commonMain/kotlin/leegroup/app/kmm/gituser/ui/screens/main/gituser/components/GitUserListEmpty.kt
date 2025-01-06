package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import leegroup.app.kmm.gituser.stringRetry
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GitUserListEmpty(modifier: Modifier = Modifier, onRefresh: () -> Unit) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Button(onClick = onRefresh) {
            Text(
                text = stringRetry(),
                style = MaterialTheme.typography.subtitle2
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