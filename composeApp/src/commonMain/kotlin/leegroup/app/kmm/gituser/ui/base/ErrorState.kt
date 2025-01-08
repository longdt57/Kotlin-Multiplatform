package leegroup.app.kmm.gituser.ui.base

import androidx.compose.runtime.Composable
import leegroup.app.kmm.gituser.stringClose
import leegroup.app.kmm.gituser.stringPopupErrorNoConnectionBody
import leegroup.app.kmm.gituser.stringPopupErrorNoConnectionTitle
import leegroup.app.kmm.gituser.stringPopupErrorTimeOutBody
import leegroup.app.kmm.gituser.stringPopupErrorTimeOutTitle
import leegroup.app.kmm.gituser.stringPopupErrorUnknownBody
import leegroup.app.kmm.gituser.stringPopupErrorUnknownTitle
import leegroup.app.kmm.gituser.stringRetry

sealed interface ErrorState {
    data object None : ErrorState

    interface MessageError : ErrorState {
        val errorCode: Int? get() = null
        val icon: (@Composable () -> Unit)? get() = null
        val title: @Composable () -> String get() = { stringPopupErrorUnknownTitle() }
        val message: @Composable () -> String get() = { stringPopupErrorUnknownBody() }
        val confirmText: (@Composable () -> String)? get() = { stringClose() }
        val dismissText: (@Composable () -> String)? get() = null
    }

    data object Common : MessageError

    data class Network(
        override val errorCode: Int? = null,
        override val title: @Composable () -> String = { stringPopupErrorNoConnectionTitle() },
        override val message: @Composable () -> String = { stringPopupErrorNoConnectionBody() },
        override val confirmText: @Composable () -> String = { stringRetry() },
        override val dismissText: @Composable () -> String = { stringClose() }
    ) : MessageError

    data class Api(
        override val errorCode: Int? = null,
        override val title: @Composable () -> String = { stringPopupErrorUnknownTitle() },
        override val message: @Composable () -> String = { stringPopupErrorUnknownBody() },
        override val confirmText: @Composable () -> String = { stringRetry() },
        override val dismissText: @Composable () -> String = { stringClose() },
        val customMessage: String?,
    ) : MessageError

    data class Server(
        override val errorCode: Int? = null,
        override val title: @Composable () -> String = { stringPopupErrorTimeOutTitle() },
        override val message: @Composable () -> String = { stringPopupErrorTimeOutBody() },
        override val confirmText: (@Composable () -> String)? = null,
        override val dismissText: @Composable () -> String = { stringClose() },
    ) : MessageError
}
