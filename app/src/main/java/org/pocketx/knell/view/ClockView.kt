package org.pocketx.knell.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import org.pocketx.knell.R

/**
 * 模拟时钟
 * TODO(宽度暂写死, 调整样式)
 *
 * @author zhangchao
 * @author shenghaiyang
 */
class ClockView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val mHourColor: Int
    private val mMinuteColor: Int
    private val mSecondColor: Int
    private val mEdgeColor: Int

    private var mCenterX = 0f
    private var mCenterY = 0f
    private var mRadius = 0f

    private val mPath = Path()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mHour = 0
    private var mMinute = 0
    private var mSecond = 0

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView, defStyleAttr, 0)
        mHourColor = typedArray.getColor(R.styleable.ClockView_hourColor, Color.BLACK)
        mMinuteColor = typedArray.getColor(R.styleable.ClockView_minuteColor, Color.BLACK)
        mSecondColor = typedArray.getColor(R.styleable.ClockView_secondColor, Color.RED)
        mEdgeColor = typedArray.getColor(R.styleable.ClockView_edgeColor, Color.BLACK)
        typedArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = w / 2f
        mCenterY = h / 2f
        mRadius = mCenterX - 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)
        drawHours(canvas)
        drawMinutes(canvas)
        drawSeconds(canvas)
    }

    /**
     * 绘制表盘
     */
    private fun drawCircle(canvas: Canvas) {
        mPaint.color = mEdgeColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        mPath.reset()
        mPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW)
        canvas.drawPath(mPath, mPaint)
    }

    /**
     * 绘制时针
     */
    private fun drawHours(canvas: Canvas) {
        canvas.save()
        val hourWidth = 6
        mPaint.strokeWidth = hourWidth.toFloat()
        mPath.reset()
        mPath.addRect(
            ((width - hourWidth) / 2).toFloat(), dp2px(70f).toFloat(),
            ((width + hourWidth) / 2).toFloat(), (height / 2 + dp2px(10f)).toFloat(), Path.Direction.CW
        )
        canvas.rotate((360 / 12 * mHour).toFloat(), mCenterX, mCenterY)
        canvas.drawPath(mPath, mPaint)
        canvas.restore()
    }

    /**
     * 绘制分针
     */
    private fun drawMinutes(canvas: Canvas) {
        canvas.save()
        mPath.reset()
        mPaint.color = mMinuteColor
        val minuteWidth = 4
        mPaint.strokeWidth = minuteWidth.toFloat()
        mPath.addRect(
            ((width - minuteWidth) / 2).toFloat(), dp2px(50f).toFloat(),
            ((width + minuteWidth) / 2).toFloat(), (height / 2 + dp2px(15f)).toFloat(), Path.Direction.CW
        )
        canvas.rotate((360 / 60 * mMinute).toFloat(), mCenterX, mCenterY)
        canvas.drawPath(mPath, mPaint)
        canvas.restore()
    }

    /**
     * 绘制秒针
     */
    private fun drawSeconds(canvas: Canvas) {
        //秒针
        canvas.save()
        mPaint.color = mSecondColor
        val secondWidth = 2
        mPaint.strokeWidth = secondWidth.toFloat()
        mPath.reset()
        mPath.addRect(
            ((width - secondWidth) / 2).toFloat(), dp2px(30f).toFloat(),
            ((width + secondWidth) / 2).toFloat(), (height / 2 + dp2px(20f)).toFloat(), Path.Direction.CW
        )
        canvas.rotate((360 / 60 * mSecond).toFloat(), mCenterX, mCenterY)
        canvas.drawPath(mPath, mPaint)
        canvas.restore()

        //画一个红心圆
        mPaint.color = Color.RED
        mPaint.strokeWidth = 1f
        mPaint.style = Paint.Style.FILL
        mPath.reset()
        mPath.addCircle(mCenterY, mCenterY, dp2px(3f).toFloat(), Path.Direction.CW)
        canvas.drawPath(mPath, mPaint)
    }

    private fun dp2px(dp: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)

    /**
     * 设置时间
     *
     * @param hour   时
     * @param minute 分
     * @param second 秒
     */
    fun setClockData(hour: Int, minute: Int, second: Int) {
        this.mHour = hour
        this.mMinute = minute
        this.mSecond = second
        invalidate()
    }

}
