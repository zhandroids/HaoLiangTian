package com.hlt.hao.company

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hlt.hao.ApiService
import com.hlt.hao.http.RetrofitClient
import kotlinx.coroutines.launch
import com.google.gson.annotations.SerializedName
import com.hlt.hao.http.LiveDataResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.Serializable


/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc
 */
class GrainListViewModel : ViewModel() {

    private val liveData: MutableLiveData<LiveDataResponse<CompanyListResponse>> by lazy { MutableLiveData<LiveDataResponse<CompanyListResponse>>() }

    fun getGrainList(pageNo:String,pageSize:String): MutableLiveData<LiveDataResponse<CompanyListResponse>> {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

            Log.e("asker","异常信息${throwable.localizedMessage}")
            val liveDataResponse = LiveDataResponse<CompanyListResponse>()
            liveDataResponse.isState = false
            liveData.value = liveDataResponse

        }


        viewModelScope.launch(context = exceptionHandler) {
            val grainList
                    = RetrofitClient.getApiService(ApiService::class.java).getCompanyList()
            val liveDataResponse = LiveDataResponse<CompanyListResponse>()
            liveDataResponse.isState = true
            liveDataResponse.data = grainList
            liveData.value = liveDataResponse

        }

        return liveData
    }

}

data class CompanyListResponse(
        @SerializedName("data")
        val data: MutableList<CompanyItemData>?,
        @SerializedName("message")
        val message: String?,
        @SerializedName("success")
        val success: Boolean?
)

data class CompanyData (
        @SerializedName("total")
        val success: Int?,

        @SerializedName("list")
val list: MutableList<CompanyItemData>?
)

data class CompanyItemData(
    @SerializedName("bz")
    val bz: Any?,
    @SerializedName("clrq")
    val clrq: Any?,
    @SerializedName("dwbh")
    val dwbh: String?,
    @SerializedName("dwcz")
    val dwcz: Any?,
    @SerializedName("dwdz")
    val dwdz: String?,
    @SerializedName("dwjj")
    val dwjj: Any?,
    @SerializedName("dwmc")
    val dwmc: String?,
    @SerializedName("dwrs")
    val dwrs: Int?,
    @SerializedName("dwtp")
    val dwtp: Any?,
    @SerializedName("dwyx")
    val dwyx: Any?,
    @SerializedName("dwzb")
    val dwzb: Any?,
    @SerializedName("dwzt")
    val dwzt: Int?,
    @SerializedName("fhr_yhid")
    val fhrYhid: Any?,
    @SerializedName("fhsj")
    val fhsj: Any?,
    @SerializedName("fhyj")
    val fhyj: Any?,
    @SerializedName("frdb")
    val frdb: String?,
    @SerializedName("fzrlxdh")
    val fzrlxdh: String?,
    @SerializedName("fzrxm")
    val fzrxm: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("jszn")
    val jszn: String?,
    @SerializedName("mdw_dwid")
    val mdwDwid: Any?,
    @SerializedName("tbr_yhid")
    val tbrYhid: Any?,
    @SerializedName("tbsj")
    val tbsj: Any?,
    @SerializedName("yyfw")
    val yyfw: Any?,
    @SerializedName("yyqxjzsj")
    val yyqxjzsj: Any?,
    @SerializedName("yyqxqssj")
    val yyqxqssj: Any?,
    @SerializedName("yyzztp")
    val yyzztp: Any?,
    @SerializedName("zczb")
    val zczb: Any?,
    @SerializedName("zxly")
    val zxly: Any?
):Serializable




