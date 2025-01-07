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
        val iconRes: (@Composable () -> Unit)? get() = null
        val titleRes: @Composable () -> String get() = { stringPopupErrorUnknownTitle() }
        val messageRes: @Composable () -> String get() = { stringPopupErrorUnknownBody() }
        val primaryRes: (@Composable () -> String)? get() = { stringClose() }
        val secondaryRes: (@Composable () -> String)? get() = null
    }

    data object Common : MessageError

    data class Network(
        override val errorCode: Int? = null,
        override val titleRes: @Composable () -> String = { stringPopupErrorNoConnectionTitle() },
        override val messageRes: @Composable () -> String = { stringPopupErrorNoConnectionBody() },
        override val primaryRes: @Composable () -> String = { stringRetry() },
        override val secondaryRes: @Composable () -> String = { stringClose() }
    ) : MessageError

    data class Api(
        override val errorCode: Int? = null,
        override val titleRes: @Composable () -> String = { stringPopupErrorUnknownTitle() },
        override val messageRes: @Composable () -> String = { stringPopupErrorUnknownBody() },
        override val primaryRes: @Composable () -> String = { stringRetry() },
        override val secondaryRes: @Composable () -> String = { stringClose() },
        val message: String?,
    ) : MessageError

    data class Server(
        override val errorCode: Int? = null,
        override val titleRes: @Composable () -> String = { stringPopupErrorTimeOutTitle() },
        override val messageRes: @Composable () -> String = { stringPopupErrorTimeOutBody() },
        override val primaryRes: (@Composable () -> String)? = null,
        override val secondaryRes: @Composable () -> String = { stringClose() },
    ) : MessageError
}
