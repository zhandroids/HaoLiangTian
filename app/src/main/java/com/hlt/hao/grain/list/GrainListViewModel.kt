package com.hlt.hao.grain.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hlt.hao.ApiService
import com.hlt.hao.http.RetrofitClient
import kotlinx.coroutines.launch
import com.google.gson.annotations.SerializedName
import com.hlt.hao.grain.add.AddGrainResponse
import com.hlt.hao.http.LiveDataResponse
import kotlinx.coroutines.CoroutineExceptionHandler


/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc
 */
class GrainListViewModel : ViewModel() {

    private val liveData: MutableLiveData<LiveDataResponse<GrainListResponse>> by lazy { MutableLiveData<LiveDataResponse<GrainListResponse>>() }
    private val liveDataDelete: MutableLiveData<LiveDataResponse<AddGrainResponse>> by lazy { MutableLiveData<LiveDataResponse<AddGrainResponse>>() }

    fun getGrainList(pageNo:String,pageSize:String): MutableLiveData<LiveDataResponse<GrainListResponse>> {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

            Log.e("asker","异常信息${throwable.localizedMessage}")
            val liveDataResponse = LiveDataResponse<GrainListResponse>()
            liveDataResponse.isState = false
            liveData.value = liveDataResponse
        }
        viewModelScope.launch(context = exceptionHandler) {
            val grainList
                    = RetrofitClient.getApiService(ApiService::class.java).grainList(pageNo,pageSize)
            val liveDataResponse = LiveDataResponse<GrainListResponse>()
            liveDataResponse.isState = true
            liveDataResponse.data = grainList
            liveData.value = liveDataResponse
        }
        return liveData
    }

    fun deleteOrder(id:String): MutableLiveData<LiveDataResponse<AddGrainResponse>> {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

            Log.e("asker","异常信息${throwable.localizedMessage}")
            val liveDataResponse = LiveDataResponse<AddGrainResponse>()
            liveDataResponse.isState = false
            liveDataDelete.value = liveDataResponse
        }
        viewModelScope.launch(context = exceptionHandler) {
            val grainList
                    = RetrofitClient.getApiService(ApiService::class.java).deleteOrder(id)
            val liveDataResponse = LiveDataResponse<AddGrainResponse>()
            liveDataResponse.isState = true
            liveDataResponse.data = grainList
            liveDataDelete.value = liveDataResponse
        }
        return liveDataDelete
    }

}

data class GrainListResponse(
        @SerializedName("data")
        val data: GrainData?,
        @SerializedName("message")
        val message: String?,
        @SerializedName("success")
        val success: Boolean?
)

data class GrainData (
        @SerializedName("total")
        val success: Int?,

        @SerializedName("list")
val list: MutableList<GrainListData>?
)

data class GrainListData(
        @SerializedName("bz")
        val bz: String?,
        @SerializedName("ddds")
        val ddds: String?,
        @SerializedName("ddyxq")
        val ddyxq: String?,
        @SerializedName("dwmc")
        val dwmc: String?,
        @SerializedName("fbsj")
        val fbsj: String?,
        @SerializedName("fhrYhid")
        val fhrYhid: String?,
        @SerializedName("fhyj")
        val fhyj: String?,
        @SerializedName("fhzt")
        val fhzt: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("jsdwmc")
        val jsdwmc: String?,
        @SerializedName("lsddfbDwid")
        val lsddfbDwid: String?,
        @SerializedName("lsddfbbh")
        val lsddfbbh: String?,
        @SerializedName("lspz")
        val lspz: String?,
        @SerializedName("lszddjyq")
        val lszddjyq: String?,
        @SerializedName("lszlyq")
        val lszlyq: String?,
        @SerializedName("sgck")
        val sgck: String?,
        @SerializedName("sgj")
        val sgj: String?,
        @SerializedName("tjsj")
        val tjsj: String?,
        @SerializedName("zzqjsrq")
        val zzqjsrq: String?,
        @SerializedName("zzqksrq")
        val zzqksrq: String?,
        @SerializedName("zzqy")
        val zzqy: String?,
        @SerializedName("zzzq")
        val zzzq: String?
)




