package com.hlt.hao.grain.add

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
class AddGrainViewModel : ViewModel() {

    private val loginResponseLiveData: MutableLiveData<LiveDataResponse<AddGrainResponse>> by lazy { MutableLiveData<LiveDataResponse<AddGrainResponse>>() }


    /**
     * 增加订单
     * @param[dwmc] 单位名称
     * @param[lspz] 粮食品种
     * @param[lszlyq]粮食质量要求
     * @param[zzqy] 种植区域
     * @param[zzzq] 种植周期
     * @param[ddds] 订单吨数
     * @param[ddyxq] 订单有效期
     * @param[thsj] 申请时间
     */
    fun add(dwmc: String,
            lspz: String,
            lszlyq: String,
            zzqy: String,
            zzzq: String,
            ddds: String,
            ddyxq: String,
            tjsj: String): MutableLiveData<LiveDataResponse<AddGrainResponse>> {

        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
            val liveDataResponse = LiveDataResponse<AddGrainResponse>()
            liveDataResponse.isState = false
            loginResponseLiveData.value = liveDataResponse

        }
        viewModelScope.launch(context = coroutineExceptionHandler) {

            val response = RetrofitClient.getApiService(ApiService::class.java).addOrder(
                  dwmc,
                    lspz,
                    lszlyq,
                    zzqy,
                    zzzq,
                    ddds,
                    ddyxq,
                    tjsj
            )

            val liveDataResponse = LiveDataResponse<AddGrainResponse>()
            liveDataResponse.isState = true
            liveDataResponse.data = response

            loginResponseLiveData.value = liveDataResponse
        }
        return loginResponseLiveData
    }
}