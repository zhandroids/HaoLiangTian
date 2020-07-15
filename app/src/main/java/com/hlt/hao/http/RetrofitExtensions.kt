package com.hlt.hao.http

import com.hlt.hao.http.RetrofitClient.getApiService

/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc
 */

inline fun <reified T> createApiService(): T = getApiService(T::class.java)
