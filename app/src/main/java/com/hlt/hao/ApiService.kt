package com.hlt.hao

import com.hlt.hao.company.CompanyListResponse
import com.hlt.hao.grain.add.AddGrainResponse
import com.hlt.hao.grain.add.GrainImageResponse
import com.hlt.hao.grain.add.UploadResponse
import com.hlt.hao.grain.list.GrainListResponse
import com.hlt.hao.login.LoginResponse
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc
 */

interface ApiService {

    /**
     * 登录好粮田
     */
    @POST("yhjbxx/loginAjax/")
    suspend fun userLogin(@Query("yhm") userName: String,
                          @Query("yhmm") password: String,
                          @Query("rememberMe") rememberMe: String): LoginResponse

    /**
     * 粮食订单发布
     */
    @POST("lsddfb/list/")
    suspend fun grainList(@Query("pageNo") pageNo: String, @Query("pageSize") pageSize: String): GrainListResponse

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
    @POST("lsddfb/insert")
    suspend fun addOrder(@Query("dwmc") dwmc: String,
                         @Query("lspz") lspz: String,
                         @Query("lszlyq") lszlyq: String,
                         @Query("zzqy") zzqy: String,
                         @Query("zzzq") zzzq: String,
                         @Query("ddds") ddds: String,
                         @Query("ddyxq") ddyxq: String,
                         @Query("tjsj") thsj: String,
                         @Query("fhzt") fhzt: String = "0"
    ): AddGrainResponse

    /**
     * 删除订单
     */
    @POST("lsddfb/delete")
    suspend fun deleteOrder(@Query("id") orderId: String): AddGrainResponse

    /**
     * 修改订单
     */
    @POST("yhjbxx/loginAjax/")
    suspend fun modifyOrder()

    /**
     * 获取单位列表
     */
    @GET("/dwjbxx/listAll")
    suspend fun getCompanyList(): CompanyListResponse

    /**
     * 上传图片
     */
    @POST("/fileUp")
    @Multipart
    suspend fun uploadImage(@Query("refId") refId: String,
                            @Query("tab") tab: String,
                            @Query("type") type: String = "photo",
                            @Part file: List<MultipartBody.Part>): UploadResponse

    /**
     * 获取一个图片
     */
    @GET("/filesFind")
    suspend fun getSingleImage(@Query("refId") refId: String,
                               @Query("tab") tab: String,
                               @Query("type") type: String = "photo"
    ): GrainImageResponse


}