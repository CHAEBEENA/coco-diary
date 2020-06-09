package com.chaebeen.coco.ui.add

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

open abstract class ShapeMask : View{

    private var mProgress = 0f
    private var mMaskColor = 0
    private var mMaskPaint: Paint? = null
    private var mMargin = 0f
    private var mMaskOvalRect = RectF()
    private val mMaskPath = Path()

    enum class Direction {
        /**
         * Left to Right
         */
        LTR,
        /**
         * Right to Left
         */
        RTL,
        /**
         * Up to Down
         */
        UTD,
        /**
         * Down to Up
         */
        DTU
    }

    protected var mDirection: Direction = Direction.DTU

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mMaskPaint = Paint()
        mMaskPaint!!.style = Paint.Style.FILL
        mMaskPaint!!.isAntiAlias = true
    }

    open fun setMaskColor(color: Int) {
        mMaskColor = color
        mMaskPaint!!.color = mMaskColor
    }
    open fun setDirection(direction: Direction) {
        mDirection = direction
    }

    open fun setMargin(margin: Float) {
        mMargin = margin
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawMask(canvas)

    }

    fun drawMask(canvas: Canvas) {
        mMaskPath.reset()

        val width = width - mMargin * 2
        val height = height - mMargin * 2
        val left = left + mMargin
        val right = right - mMargin
        val top = top + mMargin
        val bottom = bottom - mMargin
        mMaskOvalRect[left, top, right] = bottom

        when (mDirection) {
            Direction.LTR -> {
                val x: Float = mProgress * width / 100 + left
                mMaskPath.addRect(x, top, right, bottom, Path.Direction.CW)
            }
            Direction.RTL -> {
                val x: Float = width - mProgress * width / 100 + left
                mMaskPath.addRect(left, top, x, bottom, Path.Direction.CW)
            }
            Direction.UTD -> {
                val y: Float = mProgress * height / 100 + top
                mMaskPath.addRect(left, y, right, bottom, Path.Direction.CW)
            }
            Direction.DTU -> {
                val y: Float = height - mProgress * height / 100 + top
                mMaskPath.addRect(left, top, right, y, Path.Direction.CW)
            }
        }

        canvas.clipPath(getClipPath(), Region.Op.REPLACE)
        canvas.drawPath(mMaskPath, mMaskPaint!!)
    }

    protected abstract fun getClipPath(): Path
}




























