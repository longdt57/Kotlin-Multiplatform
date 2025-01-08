package leegroup.app.kmm.gituser.ui.base

import androidx.compose.runtime.Composable

@Composable
fun ErrorView(
    error: ErrorState,
    onErrorConfirmation: (ErrorState) -> Unit = {},
    onErrorDismissRequest: (ErrorState) -> Unit = {},
) {
    when (error) {
        is ErrorState.MessageError -> {
            val message = when (error) {
                is ErrorState.Api -> error.customMessage ?: error.message()
                else -> error.message()
            }
            AlertDialogView(
                dialogTitle = error.title(),
                dialogText = message,
                confirmText = error.confirmText?.let { it() },
                dismissText = error.dismissText?.let { it() },
                onConfirmation = { onErrorConfirmation(error) },
                onDismissRequest = { onErrorDismissRequest(error) })
        }

        is ErrorState.None -> {}
    }
}
