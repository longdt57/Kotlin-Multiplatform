package leegroup.module.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val DesignSystemIcons.ErrorRed18Dp: ImageVector
    get() {
        if (_ErrorRed18Dp != null) {
            return _ErrorRed18Dp!!
        }
        _ErrorRed18Dp = ImageVector.Builder(
            name = "ErrorRed18Dp",
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
                path(fill = SolidColor(Color(0xFFFF3E1C))) {
                    moveTo(7.523f, 6.727f)
                    curveTo(7.303f, 6.508f, 6.947f, 6.508f, 6.727f, 6.727f)
                    curveTo(6.508f, 6.947f, 6.508f, 7.303f, 6.727f, 7.523f)
                    lineTo(8.204f, 9f)
                    lineTo(6.727f, 10.477f)
                    curveTo(6.508f, 10.697f, 6.508f, 11.053f, 6.727f, 11.273f)
                    curveTo(6.947f, 11.492f, 7.303f, 11.492f, 7.523f, 11.273f)
                    lineTo(9f, 9.795f)
                    lineTo(10.477f, 11.273f)
                    curveTo(10.697f, 11.492f, 11.053f, 11.492f, 11.273f, 11.273f)
                    curveTo(11.492f, 11.053f, 11.492f, 10.697f, 11.273f, 10.477f)
                    lineTo(9.795f, 9f)
                    lineTo(11.273f, 7.523f)
                    curveTo(11.492f, 7.303f, 11.492f, 6.947f, 11.273f, 6.727f)
                    curveTo(11.053f, 6.508f, 10.697f, 6.508f, 10.477f, 6.727f)
                    lineTo(9f, 8.205f)
                    lineTo(7.523f, 6.727f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF3E1C)),
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

        return _ErrorRed18Dp!!
    }

@Suppress("ObjectPropertyName")
private var _ErrorRed18Dp: ImageVector? = null
