package leegroup.module.designsystem.support.extensions

import gituserkmm.core.designsystem.generated.resources.Res
import gituserkmm.core.designsystem.generated.resources.popup_error_no_connection_title
import gituserkmm.core.designsystem.generated.resources.popup_error_unknown_title
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.CancellationException
import kotlinx.io.IOException
import leegroup.module.designsystem.ui.models.ErrorModel
import leegroup.module.designsystem.ui.models.ErrorState
import leegroup.module.designsystem.ui.models.Message
import org.jetbrains.compose.resources.StringResource

suspend fun Throwable.mapToErrorDialog(): ErrorState {
    return when (this) {
        is UnresolvedAddressException,
        is IOException,
        is CancellationException -> ErrorState.Network

        is ConnectTimeoutException -> ErrorState.Server
        is ClientRequestException -> ErrorState.Api(asErrorModel())
        else -> ErrorState.Common
    }
}

private val errorMapping = mapOf<Int, StringResource>(
    // TODO add error code here
)

@Suppress("MagicNumber")
internal suspend fun Throwable.mapToMessage(): Message {
    return when (this) {
        is ErrorModel -> {
            Message.SnackBarMessage.buildError(
                getString(
                    msgId = errorMapping[code],
                    debugMsg = message
                )
            )
        }

        is ClientRequestException -> {
            val error = asErrorModel()
            Message.SnackBarMessage.buildError(
                getString(
                    msgId = errorMapping[error?.code],
                    debugMsg = message
                )
            )
        }

        is UnresolvedAddressException,
        is ConnectTimeoutException,
        is CancellationException -> {
            Message.SnackBarMessage.buildError(
                getString(
                    msgId = Res.string.popup_error_no_connection_title,
                    debugMsg = message
                )
            )
        }

        else -> {
            Message.SnackBarMessage.buildError(
                getString(
                    msgId = Res.string.popup_error_unknown_title,
                    debugMsg = message
                )
            )
        }
    }
}

internal suspend fun Throwable.asErrorModel(): ErrorModel? {
    return when (this) {
        is ClientRequestException -> response.body<ErrorModel>()
        else -> null
    }
}
