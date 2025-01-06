package leegroup.app.kmm.gituser.di

import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListViewModel
import leegroup.app.kmm.gituser.support.util.DispatchersProvider
import leegroup.app.kmm.gituser.support.util.DispatchersProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModule: Module = module {
    single<DispatchersProvider> { DispatchersProviderImpl() }

    // ViewModels
    viewModel { GitUserListViewModel(get(), get()) }
}
