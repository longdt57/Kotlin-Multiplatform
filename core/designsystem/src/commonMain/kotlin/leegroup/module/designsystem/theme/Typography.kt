package leegroup.module.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import gituserkmm.core.designsystem.generated.resources.Res
import gituserkmm.core.designsystem.generated.resources.montserrat_light
import gituserkmm.core.designsystem.generated.resources.montserrat_medium
import gituserkmm.core.designsystem.generated.resources.montserrat_regular
import gituserkmm.core.designsystem.generated.resources.montserrat_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun fontFamily() = FontFamily(
    Font(Res.font.montserrat_light, FontWeight.Light),
    Font(Res.font.montserrat_regular, FontWeight.Normal),
    Font(Res.font.montserrat_medium, FontWeight.Medium),
    Font(Res.font.montserrat_semibold, FontWeight.SemiBold)
)
