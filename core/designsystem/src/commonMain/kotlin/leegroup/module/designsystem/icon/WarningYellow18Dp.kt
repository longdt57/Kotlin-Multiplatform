package leegroup.module.designsystem.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val DesignSystemIcons.WarningYellow18Dp: ImageVector
    get() {
        if (_WarningYellow18Dp != null) {
            return _WarningYellow18Dp!!
        }
        _WarningYellow18Dp = ImageVector.Builder(
            name = "WarningYellow18Dp",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 18f,
            viewportHeight = 18f
        ).apply {
            path(fill = SolidColor(Color(0xFFFBBC05))) {
                moveTo(9f, 5.438f)
                curveTo(9.311f, 5.438f, 9.562f, 5.689f, 9.562f, 6f)
                verticalLineTo(9.75f)
                curveTo(9.562f, 10.061f, 9.311f, 10.313f, 9f, 10.313f)
                curveTo(8.689f, 10.313f, 8.437f, 10.061f, 8.437f, 9.75f)
                verticalLineTo(6f)
                curveTo(8.437f, 5.689f, 8.689f, 5.438f, 9f, 5.438f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFBBC05))) {
                moveTo(9f, 12.75f)
                curveTo(9.414f, 12.75f, 9.75f, 12.414f, 9.75f, 12f)
                curveTo(9.75f, 11.586f, 9.414f, 11.25f, 9f, 11.25f)
                curveTo(8.586f, 11.25f, 8.25f, 11.586f, 8.25f, 12f)
                curveTo(8.25f, 12.414f, 8.586f, 12.75f, 9f, 12.75f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFBBC05)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6.221f, 3.357f)
                curveTo(7.025f, 2.336f, 7.876f, 1.688f, 9f, 1.688f)
                curveTo(10.124f, 1.688f, 10.975f, 2.336f, 11.779f, 3.357f)
                curveTo(12.57f, 4.362f, 13.408f, 5.847f, 14.48f, 7.748f)
                lineTo(14.806f, 8.327f)
                curveTo(15.693f, 9.898f, 16.392f, 11.139f, 16.76f, 12.135f)
                curveTo(17.136f, 13.153f, 17.225f, 14.077f, 16.657f, 14.893f)
                curveTo(16.105f, 15.684f, 15.185f, 16.007f, 14.024f, 16.16f)
                curveTo(12.868f, 16.313f, 11.313f, 16.313f, 9.319f, 16.313f)
                horizontalLineTo(8.681f)
                curveTo(6.687f, 16.313f, 5.132f, 16.313f, 3.976f, 16.16f)
                curveTo(2.815f, 16.007f, 1.895f, 15.684f, 1.343f, 14.893f)
                curveTo(0.775f, 14.077f, 0.864f, 13.153f, 1.24f, 12.135f)
                curveTo(1.607f, 11.139f, 2.307f, 9.898f, 3.194f, 8.327f)
                lineTo(3.52f, 7.748f)
                curveTo(4.592f, 5.847f, 5.43f, 4.362f, 6.221f, 3.357f)
                close()
                moveTo(7.105f, 4.053f)
                curveTo(6.374f, 4.981f, 5.578f, 6.39f, 4.474f, 8.347f)
                lineTo(4.201f, 8.831f)
                curveTo(3.281f, 10.462f, 2.628f, 11.624f, 2.295f, 12.525f)
                curveTo(1.967f, 13.414f, 2.013f, 13.886f, 2.266f, 14.249f)
                curveTo(2.537f, 14.637f, 3.044f, 14.903f, 4.122f, 15.045f)
                curveTo(5.196f, 15.186f, 6.677f, 15.188f, 8.727f, 15.188f)
                horizontalLineTo(9.273f)
                curveTo(11.323f, 15.188f, 12.804f, 15.186f, 13.877f, 15.045f)
                curveTo(14.956f, 14.903f, 15.464f, 14.637f, 15.734f, 14.249f)
                curveTo(15.987f, 13.886f, 16.033f, 13.414f, 15.705f, 12.525f)
                curveTo(15.372f, 11.624f, 14.719f, 10.462f, 13.799f, 8.831f)
                lineTo(13.526f, 8.347f)
                curveTo(12.422f, 6.39f, 11.626f, 4.981f, 10.895f, 4.053f)
                curveTo(10.172f, 3.134f, 9.609f, 2.813f, 9f, 2.813f)
                curveTo(8.391f, 2.813f, 7.828f, 3.134f, 7.105f, 4.053f)
                close()
            }
        }.build()

        return _WarningYellow18Dp!!
    }

@Suppress("ObjectPropertyName")
private var _WarningYellow18Dp: ImageVector? = null
