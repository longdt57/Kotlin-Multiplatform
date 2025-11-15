package leegroup.module.gituser.di

import leegroup.module.core.util.DispatchersProvider
import leegroup.module.core.util.DispatchersProviderImpl
import leegroup.module.data.getRoomDatabase
import leegroup.module.gituser.data.local.datastore.GitUserDataStore
import leegroup.module.gituser.data.local.room.GitUserDao
import leegroup.module.gituser.data.local.room.GitUserDatabase
import leegroup.module.gituser.data.local.room.GitUserDetailDao
import leegroup.module.gituser.data.remote.GitUserApiService
import leegroup.module.gituser.data.repositories.GitUserDetailRepositoryImpl
import leegroup.module.gituser.data.repositories.GitUserRepositoryImpl
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository
import leegroup.module.gituser.domain.repositories.GitUserRepository
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailLocalUseCase
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailRemoteUseCase
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.gituser.ui.mapper.GitUserDetailUiMapper
import leegroup.module.gituser.ui.mapper.GitUserDetailUiMapperImpl
import leegroup.module.gituser.ui.screens.gituser.GitUserListViewModel
import leegroup.module.gituser.ui.screens.gituserdetail.GitUserDetailViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// Declare your shared dependencies
val gitModule: Module = module {
    single { GitUserApiService() }

    single<GitUserDataStore> { GitUserDataStore() }
    single<GitUserDatabase> { getRoomDatabase<GitUserDatabase>("git_room.db") }
    factory<GitUserDao> { get<GitUserDatabase>().gitUserDao() }
    factory<GitUserDetailDao> { get<GitUserDatabase>().gitUserDetailDao() }

    single<GitUserRepository> { GitUserRepositoryImpl(get(), get()) }
    single<GitUserDetailRepository> { GitUserDetailRepositoryImpl(get(), get()) }

    single { GetGitUserUseCase(get()) }
    single { GetGitUserDetailRemoteUseCase(get()) }
    single { GetGitUserDetailLocalUseCase(get()) }

    single<DispatchersProvider> { DispatchersProviderImpl() }

    single<GitUserDetailUiMapper> { GitUserDetailUiMapperImpl() }

    viewModel { GitUserListViewModel(get(), get()) }
    viewModel { GitUserDetailViewModel(get(), get(), get(), get(), get()) }
}
