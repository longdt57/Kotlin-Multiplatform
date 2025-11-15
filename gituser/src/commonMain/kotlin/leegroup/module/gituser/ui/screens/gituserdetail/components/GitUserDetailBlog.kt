package leegroup.module.gituser.ui.screens.gituserdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gituserkmm.core.designsystem.generated.resources.Res
import gituserkmm.core.designsystem.generated.resources.blog
import leegroup.module.designsystem.getDesignPlatform
import leegroup.module.designsystem.theme.ComposeTheme
import leegroup.module.designsystem.theme.GreySoft200
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun GitUserDetailBlog(modifier: Modifier = Modifier, blog: String) {
    if (blog.isBlank()) return
    Column(modifier) {
        Text(
            text = stringResource(Res.string.blog),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            modifier = Modifier
                .clickable {
                    getDesignPlatform().openUrl(blog)
                }
                .padding(vertical = 8.dp),
            text = blog,
            style = MaterialTheme.typography.bodyLarge,
            color = GreySoft200,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BlogPreview() {
    ComposeTheme {
        GitUserDetailBlog(blog = "https://www.google.com")
    }
}