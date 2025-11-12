package leegroup.app.kmm.gituser.di

import leegroup.app.kmm.gituser.data.remote.ApiService
import leegroup.app.kmm.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.app.kmm.gituser.domain.repositories.GitUserRepository
import leegroup.app.kmm.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.core.util.DispatchersProvider
import leegroup.module.core.util.DispatchersProviderImpl
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// Declare your shared dependencies
val sharedModule: Module = module {
    single { ApiService() }
    single<GitUserRepository> { GitUserRepositoryImpl(get()) }
    single { GetGitUserUseCase(get()) }
    single<DispatchersProvider> { DispatchersProviderImpl() }
    factory { GitUserListViewModel(get(), get()) }
}
