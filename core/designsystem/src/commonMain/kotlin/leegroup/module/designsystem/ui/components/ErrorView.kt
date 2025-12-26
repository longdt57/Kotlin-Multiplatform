package leegroup.module.designsystem.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import leegroup.module.designsystem.components.AlertDialogView
import leegroup.module.designsystem.theme.ComposeTheme
import leegroup.module.designsystem.ui.models.ErrorModel
import leegroup.module.designsystem.ui.models.ErrorState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorView(
    error: ErrorState,
    onErrorConfirmation: (ErrorState) -> Unit = {},
    onErrorDismissRequest: (ErrorState) -> Unit = {},
) {
    when (error) {
        is ErrorState.MessageError -> {
            val message = when (error) {
                is ErrorState.Api -> error.customMessage ?: stringResource(error.messageRes)
                else -> stringResource(error.messageRes)
            }
            AlertDialogView(
                icon = {
                    error.iconRes?.let {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = stringResource(error.titleRes)
                        )
                    }
                },
                title = stringResource(error.titleRes),
                text = message,
                confirmText = stringResource(error.primaryRes),
                dismissText = error.secondaryRes?.let { stringResource(it) },
                onConfirmation = { onErrorConfirmation(error) },
                onDismissRequest = { onErrorDismissRequest(error) })
        }

        is ErrorState.None -> {}
    }
}

@Preview
@Composable
private fun CommonErrorViewPreview() {
    ComposeTheme {
        ErrorView(ErrorState.Common)
    }
}

@Preview
@Composable
private fun NetworkErrorViewPreview() {
    ComposeTheme {
        ErrorView(ErrorState.Network)
    }
}

@Preview
@Composable
private fun ServerErrorViewPreview() {
    ComposeTheme {
        ErrorView(ErrorState.Server)
    }
}

@Preview
@Composable
private fun ApiErrorViewPreview() {
    ComposeTheme {
        ErrorView(ErrorState.Api())
    }
}

@Preview
@Composable
private fun CustomApiErrorViewPreview() {
    ComposeTheme {
        ErrorView(
            ErrorState.Api(
                error = ErrorModel(
                    message = "Custom message"
                )
            )
        )
    }
}


