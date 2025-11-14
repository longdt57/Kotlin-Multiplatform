package leegroup.module.gituser.di

import leegroup.module.gituser.data.remote.ApiService
import leegroup.module.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.module.gituser.domain.repositories.GitUserRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.core.util.DispatchersProvider
import leegroup.module.core.util.DispatchersProviderImpl
import leegroup.module.gituser.ui.screens.main.gituser.GitUserListViewModel
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
