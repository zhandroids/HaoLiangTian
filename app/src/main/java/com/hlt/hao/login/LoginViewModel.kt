package com.hlt.hao.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hlt.hao.ApiService
import com.hlt.hao.http.LiveDataResponse
import com.hlt.hao.http.RetrofitClient
import com.hlt.hao.utils.MD5Encode
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc
 */
class LoginViewModel :ViewModel() {

    private val loginResponseLiveData:MutableLiveData<LiveDataResponse<LoginResponse>> by lazy { MutableLiveData<LiveDataResponse<LoginResponse>>() }

//    fun aa(){
//        viewModelScope.launch {
//        }
//    }

    fun login(username: String, password: String, isRemember: String): MutableLiveData<LiveDataResponse<LoginResponse>> {

        val coroutineExceptionHandler = CoroutineExceptionHandler{ coroutineContext: CoroutineContext, throwable: Throwable ->
            val liveDataResponse = LiveDataResponse<LoginResponse>()
            liveDataResponse.isState = false
            loginResponseLiveData.value = liveDataResponse

        }
        viewModelScope.launch(context = coroutineExceptionHandler) {

            val response =  RetrofitClient.getApiService(ApiService::class.java).userLogin(
                    username,
                    MD5Encode.encrypt(password),
                    isRemember
            )

            val liveDataResponse = LiveDataResponse<LoginResponse>()
            liveDataResponse.isState = true
            liveDataResponse.data = response

            loginResponseLiveData.value = liveDataResponse
        }
        return loginResponseLiveData
    }
}