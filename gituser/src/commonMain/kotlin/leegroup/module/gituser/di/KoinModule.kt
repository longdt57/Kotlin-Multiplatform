package leegroup.module.gituser.di

import leegroup.module.gituser.data.remote.ApiService
import leegroup.module.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.module.gituser.domain.repositories.GitUserRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.core.util.DispatchersProvider
import leegroup.module.core.util.DispatchersProviderImpl
import leegroup.module.gituser.data.repositories.GitUserDetailRepositoryImpl
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailLocalUseCase
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailRemoteUseCase
import leegroup.module.gituser.ui.mapper.GitUserDetailUiMapper
import leegroup.module.gituser.ui.mapper.GitUserDetailUiMapperImpl
import leegroup.module.gituser.ui.screens.gituser.GitUserListViewModel
import leegroup.module.gituser.ui.screens.gituserdetail.GitUserDetailScreen
import leegroup.module.gituser.ui.screens.gituserdetail.GitUserDetailViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// Declare your shared dependencies
val gitModule: Module = module {
    single { ApiService() }

    single<GitUserRepository> { GitUserRepositoryImpl(get()) }
    single<GitUserDetailRepository> { GitUserDetailRepositoryImpl(get()) }

    single { GetGitUserUseCase(get()) }
    single { GetGitUserDetailRemoteUseCase(get()) }
    single { GetGitUserDetailLocalUseCase(get()) }

    single<DispatchersProvider> { DispatchersProviderImpl() }

    single<GitUserDetailUiMapper> { GitUserDetailUiMapperImpl() }

    viewModel { GitUserListViewModel(get(), get()) }
    viewModel { GitUserDetailViewModel(get(), get(), get(), get(), get()) }
}
