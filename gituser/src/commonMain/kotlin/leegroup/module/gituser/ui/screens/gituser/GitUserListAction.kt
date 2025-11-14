package leegroup.module.gituser.ui.screens.gituser

sealed interface GitUserListAction {
    data object LoadIfEmpty : GitUserListAction
    data object LoadMore : GitUserListAction
}