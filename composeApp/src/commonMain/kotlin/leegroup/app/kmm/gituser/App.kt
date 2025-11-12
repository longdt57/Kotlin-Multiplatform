package leegroup.app.kmm.gituser

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import leegroup.app.kmm.gituser.data.remote.ApiService
import leegroup.app.kmm.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.app.kmm.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListViewModel
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListScreenContent
import leegroup.module.core.util.DispatchersProviderImpl

@Composable
fun App() {
    MaterialTheme {
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