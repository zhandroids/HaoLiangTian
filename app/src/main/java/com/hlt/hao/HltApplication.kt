package com.hlt.hao

import android.app.Application
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc
 */
class HltApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}