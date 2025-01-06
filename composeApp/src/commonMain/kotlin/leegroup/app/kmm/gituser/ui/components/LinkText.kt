package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LinkText(modifier: Modifier = Modifier, url: String, onClick: (String) -> Unit) {
    Text(
        modifier = modifier
            .clickable {
                onClick(url)
            },
        text = url,
        style = MaterialTheme.typography.body1,
        color = Color.Blue,
        textDecoration = TextDecoration.Underline
    )
}

@Preview
@Composable
private fun LinkTextPreview() {
    MaterialTheme {
        LinkText(url = "https://github.com/longdt57") {}
    }
}
