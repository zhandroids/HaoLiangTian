package com.hlt.hao.grain.add
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.ArrayList


/**
 *create by zhangzhuo
 *time: 2020/7/26
 *desc
 */

data class AddGrainResponse(
        var success:Boolean,
        var message:String?,
        var data: Any

)

data class UploadResponse(
        var success:Boolean,
        var message:String?,
        var code: String?
)


data class GrainImageResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: ArrayList<Data>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("sessMap")
    val sessMap: Any?,
    @SerializedName("success")
    val success: Boolean?
)

@SuppressLint("ParcelCreator")
@Parcelize
public data class Data(
    @SerializedName("ext")
    val ext: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("refId")
    val refId: Int?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("tab")
    val tab: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: String?
):Parcelable