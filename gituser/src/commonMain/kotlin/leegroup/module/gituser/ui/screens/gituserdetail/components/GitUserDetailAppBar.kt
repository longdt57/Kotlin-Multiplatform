package leegroup.module.gituser.ui.screens.gituserdetail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import gituserkmm.core.designsystem.generated.resources.Res
import gituserkmm.core.designsystem.generated.resources.back
import gituserkmm.core.designsystem.generated.resources.git_user_detail_screen_title
import leegroup.module.designsystem.theme.ComposeTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GitUserDetailAppBar(modifier: Modifier = Modifier, onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.git_user_detail_screen_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ComposeTheme {
        GitUserDetailAppBar(onBack = {})
    }

}