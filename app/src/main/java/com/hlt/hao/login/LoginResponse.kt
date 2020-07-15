package com.hlt.hao.login
import com.google.gson.annotations.SerializedName


/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc:登录接口返回值
 */
data class LoginResponse(
        var success:Boolean,
        var message:String?,
        var sessMap:UserInfo?

)

data class UserInfo(
    @SerializedName("Yhjbxx")
    val yhjbxx: Yhjbxx?
)

data class Yhjbxx(
    @SerializedName("dwmc")
    val dwmc: String?,
    @SerializedName("fhr_yhid")
    val fhrYhid: Any?,
    @SerializedName("fhsj")
    val fhsj: Any?,
    @SerializedName("fhyj")
    val fhyj: Any?,
    @SerializedName("fhzt")
    val fhzt: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("ip")
    val ip: Any?,
    @SerializedName("jsbh")
    val jsbh: String?,
    @SerializedName("jsztp")
    val jsztp: Int?,
    @SerializedName("loginResult")
    val loginResult: Int?,
    @SerializedName("rememberMe")
    val rememberMe: Any?,
    @SerializedName("rxbdjg")
    val rxbdjg: Int?,
    @SerializedName("sfzh")
    val sfzh: Any?,
    @SerializedName("sfztp")
    val sfztp: Int?,
    @SerializedName("sjhm")
    val sjhm: Any?,
    @SerializedName("yh_dwid")
    val yhDwid: Any?,
    @SerializedName("yhbs")
    val yhbs: Int?,
    @SerializedName("yhm")
    val yhm: String?,
    @SerializedName("yhmm")
    val yhmm: String?,
    @SerializedName("yhtp")
    val yhtp: Int?,
    @SerializedName("yhxm")
    val yhxm: String?,
    @SerializedName("yhyx")
    val yhyx: Any?,
    @SerializedName("yhzc")
    val yhzc: Any?,
    @SerializedName("zjjj")
    val zjjj: Any?
)