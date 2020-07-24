package com.hlt.hao.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.hlt.hao.R
import com.hlt.hao.utils.AutoSizeUtils
import kotlinx.android.synthetic.main.view_voice.view.*
import java.time.format.TextStyle

/**
 *create by zhangzhuo
 *time: 2020/7/21
 *desc
 */
class VoiceCenterView : View, ViewPager.OnPageChangeListener {

    var centerX = 0f
    var centerY = 0f
    var circleRadius = 41f
    var redCircleRadius = 0f

    /**
     * 话筒左上角坐标
     */
    var voiceIconX = 0f
    var voiceIconY = 0f

    var isDrawVoiceIcon = false

    //X轴中心点偏移量  （56/2）+36+（70/2） = 99
    var leftTextX = 0f

    var rightTextX = 0f

    /*当前Viewpager的position*/
    var currentPosition = 0

    /*当前ViewPager偏移量*/
    var currentOffset = 0f

    private var bitmap: Bitmap? = null

    constructor(
            context: Context
    ) : super(context) {
        initView(context)
    }

    constructor(
            context: Context,
            attrs: AttributeSet?
    ) : super(context, attrs) {
        initView(context)
    }

    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        refreshData()
        circleRadius = AutoSizeUtils.dp2px(context, 41f).toFloat()
        redCircleRadius = AutoSizeUtils.dp2px(context, 32f).toFloat()

        leftTextX = width / 2f
        rightTextX = leftTextX + AutoSizeUtils.dp2px(context, 99f)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_voice_p)
    }

    private val redPaint: Paint
        get() {
            return Paint().apply {
                color = Color.parseColor("#FE4f3C")
                isAntiAlias = true
                style = Paint.Style.FILL
                alpha = if (currentOffset > 0 && currentOffset < 1) {
                    (255 * (1 - currentOffset)).toInt()
                } else {
                    255
                }

                shader = LinearGradient(centerX - redCircleRadius
                        , centerY - redCircleRadius
                        , centerX + redCircleRadius
                        , centerY + redCircleRadius
                        , Color.parseColor("#FFFF9987")
                        , Color.parseColor("#FFFE4F3C")
                        , Shader.TileMode.MIRROR)
            }
        }

    private val whitePaint: Paint
        get() {
            return Paint().apply {
                color = Color.WHITE
                style = Paint.Style.FILL
                isAntiAlias = true
                setShadowLayer(AutoSizeUtils.dp2px(context, 3f).toFloat(), 0f, 10f, Color.parseColor("#DFDFDF"))
            }
        }

    private val voiceIconPaint: Paint
        get() {
            return Paint().apply {
                style = Paint.Style.FILL
                isAntiAlias = true
                alpha = if (currentOffset > 0 && currentOffset < 1) {
                    (255 * currentOffset).toInt()
                } else {
                    if (currentPosition == 0) {
                        0
                    } else {
                        255
                    }
                }

            }
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        refreshData()
        canvas?.let {
            it.drawCircle(centerX, centerY, circleRadius, whitePaint)

            //绘制红色圆圈
            it.drawCircle(centerX,
                    centerY, redCircleRadius, redPaint)
            drawLeftTopText(it)
            drawLeftBottomText(it)
            drawRightTopText(it)
            drawRightBottomText(it)

            //绘制麦克风icon
            if (isDrawVoiceIcon) {
                if (bitmap != null) {
                    bitmap?.let { bitmap ->
                        it.drawBitmap(bitmap, voiceIconX, voiceIconY, voiceIconPaint)
                    }
                }
            }

        }
    }

    /**
     * "点击开始"文字paint
     */
    private val leftTopTextPaint: Paint
        get() {
            return Paint().apply {
                color = Color.parseColor("#808080")
                style = Paint.Style.FILL
                isAntiAlias = true
                textSize = if (currentOffset > 0 && currentOffset < 1) {
                    AutoSizeUtils.sp2px(context, 14f - 2 * currentOffset).toFloat()
                } else {
                    if (currentPosition == 0) {
                        AutoSizeUtils.sp2px(context, 14f).toFloat()

                    } else {
                        AutoSizeUtils.sp2px(context, 12f).toFloat()
                    }
                }

                textAlign = Paint.Align.CENTER
                alpha = if (currentOffset > 0 && currentOffset < 1) {
                    (255 * (1 - currentOffset)).toInt()
                } else {
                    if (currentPosition == 0) {
                        255
                    } else {
                        0
                    }
                }
            }
        }

    /**
     * 绘制左上文字 （点击开始按钮）
     */
    private fun drawLeftTopText(canvas: Canvas) {
//        val fontMetrics = leftTopTextPaint.fontMetrics
        canvas.drawText("点击开始", leftTextX, AutoSizeUtils.dp2px(context, 56f).toFloat(), leftTopTextPaint)

    }

    /**
     * "语音转文字"文字paint
     */
    private val leftBottomTextPaint: Paint
        get() {
            return Paint().apply {
                color = Color.parseColor("#555555")
                style = Paint.Style.FILL
                isAntiAlias = true
                textSize = AutoSizeUtils.sp2px(context, 14f).toFloat()
                textAlign = Paint.Align.CENTER
                typeface = if (currentPosition == 0) {
                    Typeface.DEFAULT_BOLD
                } else {
                    Typeface.DEFAULT
                }
                strokeWidth = 3f

            }
        }

    /**
     * 绘制左下文字 （语音转文字）
     *
     * */
    private fun drawLeftBottomText(canvas: Canvas) {

        // center -> 54+(70/2)

        canvas.drawText("语音转文字", leftTextX, AutoSizeUtils.dp2px(context, 217f).toFloat(), leftBottomTextPaint)
    }

    /**
     * "按住说话"文字paint
     */
    private val rightTopTextPaint: Paint
        get() {
            return Paint().apply {
                color = Color.parseColor("#808080")
                style = Paint.Style.FILL
                textSize = AutoSizeUtils.sp2px(context, 14f).toFloat()
                textAlign = Paint.Align.CENTER
                alpha = if (currentOffset > 0 && currentOffset < 1) {
                    (255 * currentOffset).toInt()
                } else {
                    if (currentPosition == 0) {
                        0
                    } else {
                        255
                    }
                }
                textSize = if (currentOffset > 0 && currentOffset < 1) {
                    AutoSizeUtils.sp2px(context, 12f + 2 * currentOffset).toFloat()
                } else {
                    if (currentPosition == 0) {
                        AutoSizeUtils.sp2px(context, 12f).toFloat()

                    } else {
                        AutoSizeUtils.sp2px(context, 14f).toFloat()
                    }
                }
            }
        }


    /**
     * 绘制右上文字 （按住说话按钮）
     */
    private fun drawRightTopText(canvas: Canvas) {
        canvas.drawText("按住说话", rightTextX, AutoSizeUtils.dp2px(context, 56f).toFloat(), rightTopTextPaint)
    }

    /**
     * "语音录入"文字paint
     */
    private val rightBottomTextPaint: Paint
        get() {
            return Paint().apply {
                color = Color.parseColor("#808080")
                style = Paint.Style.FILL
                textSize = AutoSizeUtils.sp2px(context, 14f).toFloat()
                textAlign = Paint.Align.CENTER
                typeface = if (currentPosition == 0) {
                    Typeface.DEFAULT
                } else {
                    Typeface.DEFAULT_BOLD
                }
            }
        }


    /**
     * 绘制右下文字 （语音录入）
     */
    private fun drawRightBottomText(canvas: Canvas) {
        canvas.drawText("语音录入", rightTextX, AutoSizeUtils.dp2px(context, 217f).toFloat(), rightBottomTextPaint)
    }

    private fun refreshData() {
        centerX = width / 2f
        centerY = AutoSizeUtils.dp2px(context, 136f).toFloat()

        /**
         * 计算左侧文字和右侧文字 X轴上的中心点
         */
        if (currentOffset > 0 && currentOffset < 1) {
            leftTextX = width / 2 - (AutoSizeUtils.dp2px(context, 99f) * currentOffset)
            rightTextX = width / 2 + (AutoSizeUtils.dp2px(context, 99f) * (1 - currentOffset))
        } else {
            if (currentPosition == 0) {
                leftTextX = width / 2f
                rightTextX = leftTextX + AutoSizeUtils.dp2px(context, 99f)
            } else {

                rightTextX = width / 2f
                leftTextX = width / 2f - AutoSizeUtils.dp2px(context, 99f)
            }
        }

    }


    companion object {
        //红圈半径
        const val RADIUS = 32

        //158->116  话筒移动范围
        const val VOICE_Y_BASE = 22

        const val TAG = "asker"

    }


    override fun onPageScrollStateChanged(state: Int) {

        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (currentPosition == 1) {
                leftTopTextPaint.color = Color.TRANSPARENT
                leftBottomTextPaint.typeface = Typeface.DEFAULT
                rightBottomTextPaint.typeface = Typeface.DEFAULT_BOLD
            } else if (currentPosition == 0) {
                leftTopTextPaint.color = Color.parseColor("#808080")
                leftBottomTextPaint.typeface = Typeface.DEFAULT_BOLD
                rightBottomTextPaint.typeface = Typeface.DEFAULT
            }
            invalidate()
        }

    }

    override fun onPageScrolled(position: Int, offset: Float, positionOffsetPixels: Int) {
        currentPosition = position
        currentOffset = offset
        if (position == 0) {
            if (offset < 1f && offset > 0f) {
                //根据进度计算红圈半径
                redCircleRadius = AutoSizeUtils.dp2px(context, ((1 - offset) * RADIUS)).toFloat()

                //切换进度大于0.3时 绘制滑动ICON
                if (offset > 0.3) {
                    voiceIconX = width / 2f - AutoSizeUtils.dp2px(context, 15f)
                    voiceIconY = AutoSizeUtils.dp2px(context, 116f) + (AutoSizeUtils.dp2px(context, VOICE_Y_BASE.toFloat()) * (1 - offset))
                    isDrawVoiceIcon = true
                } else {
                    isDrawVoiceIcon = false
                }

                /*以下为设置文字变化*/
                if (offset > 0.5) {


                }

                invalidate()
            } else if (offset == 0f) {

                Log.e(TAG, "onPageSelected11111111111111: position = $position")

            }

        } else if (position == 1) {

        }


    }

    override fun onPageSelected(position: Int) {

    }


}