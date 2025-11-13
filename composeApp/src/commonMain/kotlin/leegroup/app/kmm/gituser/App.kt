package leegroup.app.kmm.gituser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import leegroup.app.kmm.gituser.data.remote.ApiService
import leegroup.app.kmm.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.app.kmm.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListViewModel
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListScreenContent
import leegroup.module.core.util.DispatchersProviderImpl
import leegroup.module.designsystem.theme.ComposeTheme

@Composable
fun App() {
    ComposeTheme {
        val vm = remember {
            GitUserListViewModel(
                dispatchersProvider = DispatchersProviderImpl(),
                useCase = GetGitUserUseCase(
                    GitUserRepositoryImpl(
                        ApiService()
                    )
                )
            )
        }
        GitUserListScreenContent(viewModel = vm, onClick = {}, onLinkClick = {
            getPlatform().openUrl(it)
        })
    }
}