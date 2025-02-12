package com.example.login.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun order(): ImageVector {
    return remember{
        ImageVector.Builder(
            name = "Sort_by_alpha",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(80f, 680f)
                lineToRelative(150f, -400f)
                horizontalLineToRelative(86f)
                lineToRelative(150f, 400f)
                horizontalLineToRelative(-82f)
                lineToRelative(-34f, -96f)
                horizontalLineTo(196f)
                lineToRelative(-32f, 96f)
                close()
                moveToRelative(140f, -164f)
                horizontalLineToRelative(104f)
                lineToRelative(-48f, -150f)
                horizontalLineToRelative(-6f)
                close()
                moveToRelative(328f, 164f)
                verticalLineToRelative(-76f)
                lineToRelative(202f, -252f)
                horizontalLineTo(556f)
                verticalLineToRelative(-72f)
                horizontalLineToRelative(282f)
                verticalLineToRelative(76f)
                lineTo(638f, 608f)
                horizontalLineToRelative(202f)
                verticalLineToRelative(72f)
                close()
                moveTo(360f, 200f)
                lineToRelative(120f, -120f)
                lineToRelative(120f, 120f)
                close()
                moveTo(480f, 880f)
                lineTo(360f, 760f)
                horizontalLineToRelative(240f)
                close()
            }
        }.build()
    }
}