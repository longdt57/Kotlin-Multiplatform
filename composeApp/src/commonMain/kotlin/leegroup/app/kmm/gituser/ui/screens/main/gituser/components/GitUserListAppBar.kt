package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import gituserkmm.core.designsystem.generated.resources.git_user_list_screen_title
import gituserkmm.core.designsystem.generated.resources.Res as R
import leegroup.module.core.extensions.compose.stringResourceOrNull
import leegroup.module.designsystem.theme.ComposeTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitUserListAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResourceOrNull(R.string.git_user_list_screen_title).orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        })
}

@Preview
@Composable
private fun ScreenPreview() {
    ComposeTheme {
        GitUserListAppBar()
    }
}