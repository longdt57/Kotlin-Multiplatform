package leegroup.module.gituser.ui.screens.gituser

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import leegroup.module.gituser.domain.models.GitUserModel
import leegroup.module.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.module.core.util.DispatchersProvider
import leegroup.module.gituser.ui.models.GitUserListUiModel
import leegroup.module.data.network.ResponseMapper.asResult
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.designsystem.ui.viewmodel.BaseViewModel

class GitUserListViewModel(
    private val dispatchersProvider: DispatchersProvider,
    private val useCase: GetGitUserUseCase,
) : BaseViewModel() {

    private val _uiModel = MutableStateFlow(GitUserListUiModel())
    val uiModel = _uiModel.asStateFlow()

    fun handleAction(action: GitUserListAction) {
        when (action) {
            is GitUserListAction.LoadIfEmpty -> loadIfEmpty()
            is GitUserListAction.LoadMore -> loadMore()
        }
    }

    private fun loadIfEmpty() {
        if (isEmpty()) {
            loadMore()
        }
    }

    private fun loadMore() {
        if (isLoading()) return
        useCase(since = getSince(), perPage = PER_PAGE)
            .injectLoading()
            .asResult()
            .onEach { result ->
                result
                    .onSuccess { handleSuccess(it) }
                    .onFailure { handleErrorAsMessage(it) }
            }
            .flowOn(dispatchersProvider.io)
            .launchIn(viewModelScope)
    }

    override fun onErrorConfirmation(errorState: ErrorState) {
        super.onErrorConfirmation(errorState)
        when (errorState) {
            is ErrorState.Api, is ErrorState.Network -> loadMore()
            else -> Unit
        }
    }

    private fun handleSuccess(result: List<GitUserModel>) {
        _uiModel.update { oldValue ->
            val users = oldValue.users.plus(result)
            oldValue.copy(users = users)
        }
    }

    private fun isEmpty() = _uiModel.value.users.isEmpty()

    private fun getSince() = _uiModel.value.users.size

    companion object {
        const val PER_PAGE = 20
    }
}