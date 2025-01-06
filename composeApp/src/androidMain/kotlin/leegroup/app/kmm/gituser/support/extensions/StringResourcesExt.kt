package leegroup.app.kmm.gituser.support.extensions

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource

@Composable
@ReadOnlyComposable
fun textOrStringResource(text: String?, @StringRes id: Int?): String? {
    return when {
        text != null -> text
        id != null -> stringResource(id = id)
        else -> null
    }
}