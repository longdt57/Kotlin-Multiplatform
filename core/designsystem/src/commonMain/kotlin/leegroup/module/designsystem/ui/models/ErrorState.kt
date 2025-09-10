package leegroup.module.designsystem.ui.models

import gituserkmm.core.designsystem.generated.resources.common_close
import gituserkmm.core.designsystem.generated.resources.common_retry
import gituserkmm.core.designsystem.generated.resources.popup_error_no_connection_body
import gituserkmm.core.designsystem.generated.resources.popup_error_no_connection_title
import gituserkmm.core.designsystem.generated.resources.popup_error_timeout_body
import gituserkmm.core.designsystem.generated.resources.popup_error_timeout_title
import gituserkmm.core.designsystem.generated.resources.popup_error_unknown_body
import gituserkmm.core.designsystem.generated.resources.popup_error_unknown_title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import gituserkmm.core.designsystem.generated.resources.Res as R

interface ErrorState {
    data object None : ErrorState

    interface MessageError : ErrorState {
        val iconRes: DrawableResource? get() = null
        val titleRes: StringResource get() = R.string.popup_error_unknown_title
        val messageRes: StringResource get() = R.string.popup_error_unknown_body
        val primaryRes: StringResource get() = R.string.common_close
        val secondaryRes: StringResource? get() = null
    }

    data object Common : MessageError

    data object Network : MessageError {
        override val titleRes: StringResource = R.string.popup_error_no_connection_title
        override val messageRes: StringResource = R.string.popup_error_no_connection_body
        override val primaryRes: StringResource = R.string.common_retry
        override val secondaryRes: StringResource = R.string.common_close
    }

    data class Api(
        val error: ErrorModel? = null,
    ) : MessageError {
        val customMessage get() = error?.message

        override val titleRes: StringResource = R.string.popup_error_unknown_title
        override val messageRes: StringResource = R.string.popup_error_unknown_body
        override val primaryRes: StringResource = R.string.common_retry
        override val secondaryRes: StringResource = R.string.common_close
    }

    data object Server : MessageError {
        override val titleRes: StringResource = R.string.popup_error_timeout_title
        override val messageRes: StringResource = R.string.popup_error_timeout_body
        override val primaryRes: StringResource = R.string.common_close
    }
}
