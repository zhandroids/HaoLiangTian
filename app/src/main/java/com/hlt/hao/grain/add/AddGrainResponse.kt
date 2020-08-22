package com.hlt.hao.grain.add

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