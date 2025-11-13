package leegroup.module.designsystem.ui.models

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

sealed interface Message {

    class SnackBarMessage(
        val messageStringId: StringResource? = null,
        val alternativeMessage: String? = null,
        val type: SnackbarType,
    ) : Message {

        suspend fun getMessage(): String {
            return alternativeMessage.takeUnless { it.isNullOrBlank() } ?: messageStringId?.let {
                getString(it)
            }.orEmpty()
        }

        companion object {
            fun buildSuccess(msgId: StringResource? = null, debugMsg: String? = null) =
                SnackBarMessage(msgId, debugMsg, SnackbarType.Success)

            fun buildWarning(msgId: StringResource? = null, debugMsg: String? = null) =
                SnackBarMessage(msgId, debugMsg, SnackbarType.Warning)

            fun buildError(messageStringId: StringResource? = null, alternativeMessage: String? = null) =
                SnackBarMessage(messageStringId, alternativeMessage, SnackbarType.Error)
        }
    }

}