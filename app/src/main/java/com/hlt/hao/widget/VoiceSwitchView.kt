package com.hlt.hao.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import androidx.core.view.get
import com.hlt.hao.R
import com.hlt.hao.utils.AutoSizeUtils

/**
 *create by zhangzhuo
 *time: 2020/7/14
 *desc
 */
class VoiceSwitchView : RelativeLayout , GestureDetector.OnGestureListener{

    var centerX = 0
    var centerY = 0
    var circleRadius = 0
    var redCircleRadius = 0
    var touchSlop = 0
    /*点下时X轴坐标*/
    var mLastDownX = 0f
    /*点下时Y轴坐标*/
    var mLastDownY = 0f

    lateinit var gestureDetector:GestureDetector

    constructor(
            context: Context
    ) : super(context){
        initView(context)
    }

    constructor(
            context: Context,
            attrs: AttributeSet?
    ) : super(context, attrs){
        initView(context)
    }

    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr){
        initView(context)
    }

    private val redPaint:Paint
        get(){
            return Paint().apply {
                color = Color.RED
                style = Paint.Style.FILL
            }
        }

    private val whitePaint:Paint
        get(){
            return Paint().apply {
                color = Color.WHITE
                style = Paint.Style.FILL
            }
        }

    private fun initView(context: Context) {
        Log.e("asker","0000000000000")
        setWillNotDraw(false);

        val viewConfiguration = ViewConfiguration.get(context)
        touchSlop = viewConfiguration.scaledPagingTouchSlop

        LayoutInflater.from(context).inflate(R.layout.view_voice_switch,this)

        circleRadius = AutoSizeUtils.dp2px(context,40f)
        redCircleRadius = AutoSizeUtils.dp2px(context,31f)

        gestureDetector = GestureDetector(context,this)


    }

    private fun  refreshData(){
        centerX = width/2
        centerY = AutoSizeUtils.dp2px(context,136f)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        refreshData()
        Log.e("asker","11111111111111111")
        canvas?.let {
            Log.e("asker","222222222222222222")

            it.drawCircle(centerX.toFloat(),
                    centerY.toFloat(),circleRadius.toFloat(),whitePaint)
            it.drawCircle(centerX.toFloat(),
                    centerY.toFloat(),redCircleRadius.toFloat(),redPaint)

        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)

//        when(event?.action){
//            MotionEvent.ACTION_DOWN -> {
//                mLastDownX = event.x
//                mLastDownY = event.y
//
//            }
//
//            MotionEvent.ACTION_MOVE -> {
//
//                val currentX = event.x
//                val currentY = event.y
//
//                val diffX = x-mLastDownX
//                val diffY = y-mLastDownY
//
//                if (Math.abs(diffX)>touchSlop&&Math.abs(diffX)>Math.abs(diffY)){
//                    //如果为横向滑动
//
//                }
//
//
//            }
//
//            MotionEvent.ACTION_UP -> {
//
//            }
//
//        }

        return true
    }

//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//    }

    override fun onShowPress(e: MotionEvent?) {
        Log.e(TAG, "onShowPress: " )
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.e(TAG, "onSingleTapUp: " )
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.e(TAG, "onDown: " )
        return false
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.e(TAG, "onFling: " )
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.e(TAG, "onScroll: distanceX = ${distanceX}  distanceY = ${distanceY}" )
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    companion object{
        val TAG = VoiceSwitchView.javaClass.simpleName
    }


}