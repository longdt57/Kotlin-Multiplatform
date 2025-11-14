package leegroup.module.gituser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import leegroup.module.gituser.data.remote.ApiService
import leegroup.module.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.gituser.ui.screens.main.gituser.GitUserListViewModel
import leegroup.module.gituser.ui.screens.main.gituser.components.GitUserListScreenContent
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