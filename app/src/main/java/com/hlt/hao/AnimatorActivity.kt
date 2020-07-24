package com.hlt.hao

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_animator.*
import kotlin.math.log

/**
 *create by zhangzhuo
 *time: 2020/7/15
 *desc
 */
class AnimatorActivity :BaseActivity() {
    override fun initView() {
        textView.setOnClickListener {
//
//            val animator = ObjectAnimator.ofFloat(image,"alpha",1f,0f)
//            animator.duration = 5000
////            animator.interpolator = LinearInterpolator()
//            animator.addListener(object : Animator.AnimatorListener{
//                override fun onAnimationRepeat(animation: Animator?) {
//                }
//
//                override fun onAnimationEnd(animation: Animator?) {
//                    Log.e("asker","1111111111")
////                    endView.visibility = View.GONE
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//                }
//
//                override fun onAnimationStart(animation: Animator?) {
//                    Log.e("asker","2222222222222")
//
////                    sendView.visibility = View.VISIBLE
////                    sendView.alpha = 0f
//
//                }
//            })
//            animator.start()

            val  valueAnimator = ValueAnimator.ofFloat(1.0f,0.0f)
            valueAnimator.setDuration(3000)
            valueAnimator.addUpdateListener {
                Log.e("asker","执行过程${it.getAnimatedValue()}")
                val animatedValue = it.animatedValue as Float
                image.alpha = animatedValue


            }

            valueAnimator.start()


        }

    }

    override fun getLayoutId() = R.layout.activity_animator
}