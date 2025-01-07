package leegroup.app.kmm.gituser.di

import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModule: Module = module {
    // ViewModels
    viewModel { GitUserListViewModel(get(), get()) }
}
