package com.campus.disneycompose.utils

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.core.util.Pools

internal class CircularRevealedPainter(
    private val imageBitmap: ImageBitmap,
    private val painter: Painter
) : Painter() {

    var radius by mutableStateOf(0f, policy = neverEqualPolicy())

    override fun DrawScope.onDraw() {
        val paint = paintPool.acquire() ?: Paint()
        val shaderMatrix = Matrix()
        var scale: Float
        var dx = 0f
        var dy = 0f

        try {
            val shader = ImageShader(imageBitmap, TileMode.Clamp)
            val brush = ShaderBrush(shader)

            paint.asFrameworkPaint().apply {
                isAntiAlias = true
                isDither = true
                isFilterBitmap = true
            }

            drawIntoCanvas { canvas ->
                canvas.saveLayer(size.toRect(), paint)

                val mDrawableRect = RectF(0f, 0f, size.width, size.height)
                val bitmapWidth: Int = imageBitmap.asAndroidBitmap().width
                val bitmapHeight: Int = imageBitmap.asAndroidBitmap().height

                if (bitmapWidth * mDrawableRect.height() > mDrawableRect.width() * bitmapHeight) {
                    scale = mDrawableRect.height() / bitmapHeight.toFloat()
                    dx = (mDrawableRect.width() - bitmapWidth * scale) * 0.5f
                } else {
                    scale = mDrawableRect.width() / bitmapWidth.toFloat()
                    dy = (mDrawableRect.height() - bitmapHeight * scale) * 0.5f
                }

                // resize the matrix to scale by sx and sy.
                shaderMatrix.setScale(scale, scale)

                // post translate the matrix with the specified translation.
                shaderMatrix.postTranslate(
                    (dx + 0.5f) + mDrawableRect.left,
                    (dy + 0.5f) + mDrawableRect.top
                )

                shader.setLocalMatrix(shaderMatrix)

                drawCircle(brush, size.height * radius, Offset(size.width / 2, size.height / 2))

                canvas.restore()
            }
        } finally {
            // resets the paint and release to the pool.
            paint.asFrameworkPaint().reset()
            paintPool.release(paint)
        }
    }

    /** return the dimension size of the [ImageBitmap]'s intrinsic width and height. */
    override val intrinsicSize: Size get() = painter.intrinsicSize
}

/** paint pool which caching and reusing [Paint] instances. */
private val paintPool = Pools.SimplePool<Paint>(2)
