package leegroup.module.gituser

import androidx.compose.runtime.Composable
import leegroup.module.designsystem.theme.ComposeTheme
import leegroup.module.gituser.ui.screens.gituser.GitUserListScreen

@Composable
fun App() {
    ComposeTheme {
        GitUserListScreen(navigator = {})
    }
}