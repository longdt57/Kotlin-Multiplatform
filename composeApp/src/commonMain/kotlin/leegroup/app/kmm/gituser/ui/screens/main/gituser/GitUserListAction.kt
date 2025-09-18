package leegroup.app.kmm.gituser.ui.screens.main.gituser

sealed interface GitUserListAction {
    data object LoadIfEmpty : GitUserListAction
    data object LoadMore : GitUserListAction
}