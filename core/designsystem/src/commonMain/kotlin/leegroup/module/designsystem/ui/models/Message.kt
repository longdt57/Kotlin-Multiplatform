package leegroup.module.designsystem.ui.models

sealed interface Message {

    class SnackBarMessage(
        val message: String,
        val type: SnackbarType,
    ) : Message {

        companion object {
            fun buildSuccess(message: String?) = SnackBarMessage(
                message = message.orEmpty(),
                SnackbarType.Success
            )

            fun buildWarning(message: String?) = SnackBarMessage(
                message = message.orEmpty(),
                SnackbarType.Warning
            )

            fun buildError(
                message: String?
            ) = SnackBarMessage(
                message = message.orEmpty(),
                SnackbarType.Error
            )
        }
    }

}