package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import leegroup.app.kmm.gituser.support.extensions.formatAndOpenUrl
import leegroup.app.kmm.gituser.ui.theme.ComposeTheme

@Composable
fun LinkText(modifier: Modifier = Modifier, url: String) {
    val context = LocalContext.current
    Text(
        modifier = modifier
            .clickable {
                context.formatAndOpenUrl(url)
            },
        text = url,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Blue,
        textDecoration = TextDecoration.Underline
    )
}

@Preview(showBackground = true)
@Composable
private fun LinkTextPreview() {
    ComposeTheme {
        LinkText(url = "https://github.com/longdt57")
    }
}
