package leegroup.app.kmm.gituser.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import leegroup.app.kmm.gituser.support.extensions.textOrStringResource

@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    content: @Composable () -> Unit,
) {
    content()
    LoadingView(viewModel)
    ErrorView(viewModel)
}

@Composable
private fun LoadingView(viewModel: BaseViewModel) {
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    LoadingView(loading = loading)
}

@Composable
private fun LoadingView(loading: LoadingState) {
    when (loading) {
        is LoadingState.Loading -> {
            LoadingProgress(loading)
        }

        else -> {}
    }
}

@Composable
private fun ErrorView(viewModel: BaseViewModel) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    ErrorView(
        error = error,
        onErrorConfirmation = { viewModel.onErrorConfirmation(it) },
        onErrorDismissRequest = { viewModel.onErrorDismissClick(it) }
    )
}

@Composable
private fun ErrorView(
    error: ErrorState,
    onErrorConfirmation: (ErrorState) -> Unit = {},
    onErrorDismissRequest: (ErrorState) -> Unit = {},
) {
    when (error) {
        is ErrorState.MessageError -> {
            val message = when (error) {
                is ErrorState.Api -> textOrStringResource(
                    text = error.message,
                    id = error.messageRes
                )

                else -> stringResource(error.messageRes)
            }
            AlertDialogView(
                dialogTitle = stringResource(id = error.titleRes),
                dialogText = message,
                confirmText = error.primaryRes?.let { stringResource(id = it) },
                dismissText = error.secondaryRes?.let { stringResource(id = it) },
                onConfirmation = { onErrorConfirmation(error) },
                onDismissRequest = { onErrorDismissRequest(error) })
        }

        is ErrorState.None -> {}
    }
}
