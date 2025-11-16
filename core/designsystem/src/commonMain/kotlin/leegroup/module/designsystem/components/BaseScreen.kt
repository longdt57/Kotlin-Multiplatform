package leegroup.module.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import leegroup.module.core.extensions.compose.collectAsEffect
import leegroup.module.designsystem.ui.models.LocalTopSnackbarHostStateManager
import leegroup.module.designsystem.ui.models.Message
import leegroup.module.designsystem.ui.viewmodel.BaseViewModel

@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    loadingView: @Composable () -> Unit = { LoadingView(viewModel) },
    messageObserver: @Composable () -> Unit = { MessageObserver(viewModel) },
    content: @Composable () -> Unit,
) {
    content()
    loadingView()
    messageObserver()
    ErrorView(viewModel)
}

@Composable
fun LoadingView(viewModel: BaseViewModel) {
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    LoadingView(loading = loading)
}


@Composable
fun ErrorView(viewModel: BaseViewModel) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    ErrorView(
        error = error,
        onErrorConfirmation = { viewModel.onErrorConfirmation(it) },
        onErrorDismissRequest = { viewModel.onErrorDismissClick(it) }
    )
}

@Composable
fun MessageObserver(
    viewModel: BaseViewModel
) {
    val snackbarStateManager = LocalTopSnackbarHostStateManager.current

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job? = null

    viewModel.message.collectAsEffect { event ->
        when (event) {
            is Message.SnackBarMessage -> {
                snackbarJob?.cancel()
                snackbarJob = coroutineScope.launch {
                    val message = event.message
                    snackbarStateManager.getHostState(event.type).showSnackbar(message)
                }
            }
        }
    }
}
