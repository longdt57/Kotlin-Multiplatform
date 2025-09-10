package leegroup.module.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogView(
    icon: @Composable (() -> Unit)? = null,
    title: String?,
    text: String?,
    confirmText: String,
    dismissText: String? = null,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
) {
    AlertDialog(
        icon = icon,
        title = title?.let { { Text(text = it) } },
        text = text?.let { { Text(text = it) } },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(confirmText)
            }
        },
        dismissButton = dismissText?.let {
            {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(dismissText)
                }
            }
        }
    )
}

@Preview
@Composable
private fun AlertDialogViewPreview() {
    AlertDialogView(
        title = "Hello",
        text = "Start programming",
        confirmText = "OK",
        dismissText = "Close"
    )
}