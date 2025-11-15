package leegroup.module.gituser.ui.screens.gituserdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import leegroup.module.core.util.DispatchersProvider
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.designsystem.ui.viewmodel.StateViewModel
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailLocalUseCase
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserDetailRemoteUseCase
import leegroup.module.gituser.ui.mapper.GitUserDetailUiMapper
import leegroup.module.gituser.ui.models.GitUserDetailUiModel
import leegroup.module.gituser.ui.navigation.GitUserDestination

internal class GitUserDetailViewModel constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchersProvider: DispatchersProvider,
    private val getGitUserDetailLocalUseCase: GetGitUserDetailLocalUseCase,
    private val getGitUserDetailRemoteUseCase: GetGitUserDetailRemoteUseCase,
    private val gitUserDetailModelMapper: GitUserDetailUiMapper,
) : StateViewModel<GitUserDetailUiModel>(GitUserDetailUiModel()) {

    init {
        loadFromSavedStateHandle()
    }

    private fun loadFromSavedStateHandle() {
        val navModel = savedStateHandle.toRoute<GitUserDestination.GitUserDetail>()
        setUserLogin(navModel.login)
    }

    private fun getLocal() {
        getGitUserDetailLocalUseCase(getLogin())
            .onEach { result ->
                handleSuccess(result)
            }
            .onEach {
                hideLoading() // Hide loading if local data is available
            }
            .flowOn(dispatchersProvider.io)
            .catch {
//                Timber.e(it) // Not show local error to screen
            }
            .launchIn(viewModelScope)
    }

    private fun fetchRemote() {
        getGitUserDetailRemoteUseCase(getLogin())
            .let {
                // Show loading if data is empty
                when {
                    isDataEmpty() -> it.injectLoading()
                    else -> it
                }
            }
            .onEach { result ->
                handleSuccess(result)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e ->
                if (isDataEmpty()) {
                    handleErrorAsMessage(e)  // Show error if data is empty
                } else {
//                    Timber.e(e)
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun handleSuccess(result: GitUserDetailModel) {
        val newValue = gitUserDetailModelMapper.mapToUiModel(getUiState(), result)
        update { newValue }
    }

    private fun setUserLogin(login: String) {
        update { oldValue -> oldValue.copy(login = login) }
        getLocal()
        fetchRemote()
    }

    private fun isDataEmpty(): Boolean {
        return getUiState().name.isBlank()
    }

    private fun getLogin() = getUiState().login

    override fun onErrorConfirmation(errorState: ErrorState) {
        super.onErrorConfirmation(errorState)
        when (errorState) {
            is ErrorState.Api, is ErrorState.Network -> fetchRemote()
            else -> Unit
        }
    }
}
