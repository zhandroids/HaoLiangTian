package com.hlt.hao.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.hlt.hao.R
import com.hlt.hao.utils.AutoSizeUtils
import kotlinx.android.synthetic.main.view_voice.view.*
import kotlin.math.abs

/**
 *create by zhangzhuo
 *time: 2020/7/14
 *desc
 */
class VoiceSwitchView2 : RelativeLayout ,ViewPager.OnPageChangeListener{

    var centerX = 0
    var centerY = 0
    var circleRadius = 0
    var redCircleRadius = 0
    var touchSlop = 0
    /*点下时X轴坐标*/
    var mLastDownX = 0f
    /*点下时Y轴坐标*/
    var mLastDownY = 0f

//    lateinit var gestureDetector:GestureDetector

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
        setWillNotDraw(false);

        val viewConfiguration = ViewConfiguration.get(context)
        touchSlop = viewConfiguration.scaledPagingTouchSlop

        LayoutInflater.from(context).inflate(R.layout.view_voice,this)

        circleRadius = AutoSizeUtils.dp2px(context,40f)
        redCircleRadius = AutoSizeUtils.dp2px(context,31f)

        viewPager.addOnPageChangeListener(this)

        val view = View(context)
        view.run {
            layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundColor(Color.TRANSPARENT)
        }
        val view2 = View(context)

        view2.run {
            layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundColor(Color.TRANSPARENT)
        }
        val viewList = mutableListOf(view,view2)


        viewPager.adapter = object :PagerAdapter(){
            override fun isViewFromObject(view: View, obj: Any): Boolean {
                return view == obj
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                container.addView(viewList[position])
                return viewList[position]
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(viewList[position])
            }

            override fun getCount() = 2

        }

//        viewPager.setOnTouchListener { v, event ->
//            Log.e(TAG, "initView: ${event?.x}" )
//            false
//        }

//        gestureDetector = GestureDetector(context,this)

    }



    private fun  refreshData(){
        centerX = width/2
        centerY = AutoSizeUtils.dp2px(context,136f)

    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//        refreshData()
//        Log.e("asker","11111111111111111")
//        canvas?.let {
//            Log.e("asker","222222222222222222")
//
//            it.drawCircle(centerX.toFloat(),
//                    centerY.toFloat(),redCircleRadius.toFloat(),redPaint)
//
//            canvas.drawText("我的滑板鞋",centerX.toFloat(),centerY.toFloat(),redPaint)
//        }
//    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        gestureDetector.onTouchEvent(event)

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                mLastDownX = event.x
                mLastDownY = event.y

            }

            MotionEvent.ACTION_MOVE -> {

                val currentX = event.x
                val currentY = event.y

                val diffX = currentX-mLastDownX
                val diffY = currentY-mLastDownY

                if (abs(diffX) > touchSlop && abs(diffX) > abs(diffY)) {
                    //如果为横向滑动
                    viewPager.dispatchTouchEvent(event)
                    Log.e("asker","4444444444444")
                    return false

                }
            }

            MotionEvent.ACTION_UP -> {

            }
        }

        return true
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    companion object{
        val TAG = VoiceSwitchView2.javaClass.simpleName
    }

    override fun onPageScrollStateChanged(state: Int) {
//        Log.e(TAG, "onPageScrollStateChanged: $state" )
        voiceCenterView.onPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//        Log.e(TAG, "onPageScrolled: positionOffset = $positionOffset   position  = $position" )
        voiceCenterView.onPageScrolled(position,positionOffset,positionOffsetPixels)

    }

    override fun onPageSelected(position: Int) {
//        Log.e(TAG, "onPageSelected: $position" )
        voiceCenterView.onPageSelected(position)

    }


}