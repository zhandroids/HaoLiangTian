package com.hlt.hao

import android.os.Bundle
import androidx.annotation.CheckResult
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc
 */
 abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    abstract fun initView()

    @CheckResult
    @LayoutRes
    abstract fun getLayoutId():Int

}