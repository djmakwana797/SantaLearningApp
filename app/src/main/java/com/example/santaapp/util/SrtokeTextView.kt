package com.example.santaapp.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class StrokeTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    private var strokePaint = Paint()

    override fun onDraw(canvas: Canvas) {
        val text = text.toString()
        val originalPaint = paint

        // Draw outline
        strokePaint.set(originalPaint)
        strokePaint.style = Paint.Style.STROKE
        strokePaint.strokeWidth = 4f
        strokePaint.color = 0xFF000000.toInt() // black stroke
        strokePaint.isAntiAlias = true

        canvas.drawText(text, 0f, baseline.toFloat(), strokePaint)

        // Draw fill
        super.onDraw(canvas)
    }
}
