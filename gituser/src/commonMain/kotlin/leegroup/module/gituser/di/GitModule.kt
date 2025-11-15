package leegroup.module.gituser.di

import io.ktor.client.HttpClient
import leegroup.module.core.util.AppConfigurationProvider
import leegroup.module.core.util.DispatchersProvider
import leegroup.module.core.util.DispatchersProviderImpl
import leegroup.module.data.di.KtorHttpClientProvider
import leegroup.module.data.getRoomDatabase
import leegroup.module.gituser.data.local.datastore.GitUserDataStore
import leegroup.module.gituser.data.local.room.GitUserDao
import leegroup.module.gituser.data.local.room.GitUserDatabase
import leegroup.module.gituser.data.local.room.GitUserDetailDao
import leegroup.module.gituser.data.remote.GitUserApiService
import leegroup.module.gituser.data.remote.GitUserApiServiceImpl
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
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Declare your shared dependencies

internal const val GIT_USER_KTOR_HTTP_CLIENT = "gitUserKtorHttpClient"

val gitModule: Module = module {
    single<GitUserApiService> { GitUserApiServiceImpl(get(named(GIT_USER_KTOR_HTTP_CLIENT))) }
    single<HttpClient>(named(GIT_USER_KTOR_HTTP_CLIENT)) { provideGitUserKtorHttpClient(get()) }

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

private fun provideGitUserKtorHttpClient(
    appConfigurationProvider: AppConfigurationProvider
): HttpClient {
    return KtorHttpClientProvider.provideHttpClient(
        isLoggingEnable = appConfigurationProvider.debug,
        configs = {},
        block = {
            url("https://api.github.com/")
        }
    )
}
