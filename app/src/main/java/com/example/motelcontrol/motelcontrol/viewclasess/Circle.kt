package com.example.motelcontrol.motelcontrol.viewclasess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF
import android.graphics.Color

/**
 * Created by Claure on 7/25/2017.
 */
class Circle : View {
    private val START_ANGLE_POINT = 90

    private var paint: Paint
    private var rect: RectF

    private var angle: Float = 0.toFloat()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)  {
        val strokeWidth = 40

        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.setStrokeWidth(2f)
        //Circle color
        paint.color = Color.BLUE

        val mLayoutWidth = attrs.getAttributeValue("http://schemas.android.com/apk/res/android","layout_width")
        val mLayoutHeight = attrs.getAttributeValue("http://schemas.android.com/apk/res/android","layout_height")

        var mLayoutHeightValue = mLayoutHeight.filterNot { c -> "dip".contains(c)}
        var mLayoutWidthValue = mLayoutWidth.filterNot { c -> "dip".contains(c)}

        //size mLayoutHeightValue * mLayoutWidthValue example
        rect = RectF(strokeWidth.toFloat(), strokeWidth.toFloat(), (mLayoutWidthValue.toFloat() + strokeWidth), (mLayoutHeightValue.toFloat() + strokeWidth))

        //Initial Angle (optional, it can be zero)
        angle = 360f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(rect, 0f, angle, false, paint)
    }

    fun getAngle(): Float {
        return angle.toFloat()
    }

    fun setAngle(angle: Float) {
        this.angle = angle
    }
    fun getPaint() : Paint{
        return paint
    }
}