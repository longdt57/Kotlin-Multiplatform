package leegroup.module.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val DesignSystemIcons.CheckGreen18Dp: ImageVector
    get() {
        if (_CheckGreen18Dp != null) {
            return _CheckGreen18Dp!!
        }
        _CheckGreen18Dp = ImageVector.Builder(
            name = "CheckGreen18Dp",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 18f,
            viewportHeight = 18f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(18f)
                    verticalLineToRelative(18f)
                    horizontalLineToRelative(-18f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color(0xFF0EB033))) {
                    moveTo(12.023f, 7.523f)
                    curveTo(12.242f, 7.303f, 12.242f, 6.947f, 12.023f, 6.727f)
                    curveTo(11.803f, 6.508f, 11.447f, 6.508f, 11.227f, 6.727f)
                    lineTo(7.875f, 10.08f)
                    lineTo(6.773f, 8.977f)
                    curveTo(6.553f, 8.758f, 6.197f, 8.758f, 5.977f, 8.977f)
                    curveTo(5.758f, 9.197f, 5.758f, 9.553f, 5.977f, 9.773f)
                    lineTo(7.477f, 11.273f)
                    curveTo(7.697f, 11.492f, 8.053f, 11.492f, 8.273f, 11.273f)
                    lineTo(12.023f, 7.523f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF0EB033)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(9f, 0.938f)
                    curveTo(4.547f, 0.938f, 0.938f, 4.547f, 0.938f, 9f)
                    curveTo(0.938f, 13.453f, 4.547f, 17.063f, 9f, 17.063f)
                    curveTo(13.453f, 17.063f, 17.063f, 13.453f, 17.063f, 9f)
                    curveTo(17.063f, 4.547f, 13.453f, 0.938f, 9f, 0.938f)
                    close()
                    moveTo(2.063f, 9f)
                    curveTo(2.063f, 5.169f, 5.169f, 2.063f, 9f, 2.063f)
                    curveTo(12.832f, 2.063f, 15.938f, 5.169f, 15.938f, 9f)
                    curveTo(15.938f, 12.832f, 12.832f, 15.938f, 9f, 15.938f)
                    curveTo(5.169f, 15.938f, 2.063f, 12.832f, 2.063f, 9f)
                    close()
                }
            }
        }.build()

        return _CheckGreen18Dp!!
    }

@Suppress("ObjectPropertyName")
private var _CheckGreen18Dp: ImageVector? = null
