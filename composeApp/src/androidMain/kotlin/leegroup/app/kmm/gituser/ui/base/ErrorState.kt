package leegroup.app.kmm.gituser.ui.base

import leegroup.app.kmm.gituser.R

sealed interface ErrorState {
    data object None : ErrorState

    interface MessageError : ErrorState {
        val errorCode: Int? get() = null
        val iconRes: Int? get() = null
        val titleRes: Int get() = R.string.popup_error_unknown_title
        val messageRes: Int get() = R.string.popup_error_unknown_body
        val primaryRes: Int? get() = R.string.common_close
        val secondaryRes: Int? get() = null
    }

    data object Common : MessageError

    data class Network(
        override val errorCode: Int? = null,
        override val iconRes: Int? = null,
        override val titleRes: Int = R.string.popup_error_no_connection_title,
        override val messageRes: Int = R.string.popup_error_no_connection_body,
        override val primaryRes: Int = R.string.common_retry,
        override val secondaryRes: Int? = R.string.common_close,
    ) : MessageError

    data class Api(
        override val errorCode: Int? = null,
        override val iconRes: Int? = null,
        override val titleRes: Int = R.string.popup_error_unknown_title,
        override val messageRes: Int = R.string.popup_error_unknown_body,
        override val primaryRes: Int = R.string.common_retry,
        override val secondaryRes: Int? = R.string.common_close,
        val message: String?,
    ) : MessageError

    data class Server(
        override val errorCode: Int? = null,
        override val iconRes: Int? = null,
        override val titleRes: Int = R.string.popup_error_timeout_title,
        override val messageRes: Int = R.string.popup_error_timeout_body,
        override val primaryRes: Int? = null,
        override val secondaryRes: Int = R.string.common_close,
    ) : MessageError
}
