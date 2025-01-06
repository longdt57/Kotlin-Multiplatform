package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import leegroup.app.kmm.gituser.R
import leegroup.app.kmm.gituser.ui.theme.ComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitUserListAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.git_user_list_screen_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        })
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    ComposeTheme {
        GitUserListAppBar()
    }
}