package leegroup.app.kmm.gituser

import androidx.compose.ui.window.ComposeUIViewController
import leegroup.module.gituser.ui.screens.gituser.GitUserListViewModel
import leegroup.module.gituser.ui.screens.gituserdetail.GitUserDetailViewModel
import org.koin.mp.KoinPlatform

fun MainViewController() = ComposeUIViewController { AppNavGraph() }

fun GitUserListViewModel() = KoinPlatform.getKoin().get<GitUserListViewModel>()
fun GitUserDetailViewModel() = KoinPlatform.getKoin().get<GitUserDetailViewModel>()
