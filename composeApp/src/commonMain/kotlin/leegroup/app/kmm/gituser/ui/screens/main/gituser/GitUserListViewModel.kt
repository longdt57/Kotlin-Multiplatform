package leegroup.app.kmm.gituser.ui.screens.main.gituser

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.domain.usecases.gituser.GetGitUserUseCase
import leegroup.app.kmm.gituser.support.util.DispatchersProvider
import leegroup.app.kmm.gituser.ui.base.BaseViewModel
import leegroup.app.kmm.gituser.ui.base.ErrorState
import leegroup.app.kmm.gituser.ui.models.GitUserListUiModel

open class GitUserListViewModel(
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
            .onEach { result ->
                handleSuccess(result)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e ->
                handleError(e)
            }
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
