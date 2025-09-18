package leegroup.module.designsystem.support.extensions

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun stringResourceOrNull(id: StringResource?): String? {
    return when (id) {
        null -> null
        else -> stringResource(id)
    }
}