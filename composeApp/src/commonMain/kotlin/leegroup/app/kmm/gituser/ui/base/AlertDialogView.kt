package leegroup.app.kmm.gituser.ui.base

import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogView(
    dialogTitle: String? = null,
    dialogText: String? = null,
    confirmText: String? = null,
    dismissText: String? = null,
    icon: ImageVector? = null,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
) {
    AlertDialog(
        title = {
            icon?.let {
                Icon(icon, contentDescription = "Icon")
            }
            if (dialogTitle != null) {
                Text(text = dialogTitle)
            }
        },
        text = {
            if (dialogText != null) {
                Text(text = dialogText)
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            confirmText?.let {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(confirmText)
                }
            }
        },
        dismissButton = {
            dismissText?.let {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
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
        dialogTitle = "Hello",
        dialogText = "Start programming",
        confirmText = "OK",
        dismissText = "Close"
    )
}