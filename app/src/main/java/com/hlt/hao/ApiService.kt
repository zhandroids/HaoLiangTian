package com.hlt.hao

import com.hlt.hao.list.GrainListResponse
import com.hlt.hao.login.LoginResponse
import retrofit2.http.POST
import retrofit2.http.Query

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
                          @Query("rememberMe") rememberMe: String):LoginResponse

    /**
     * 粮食订单发布
     */
    @POST("lsddfb/list/")
    suspend fun grainList(@Query("pageNo")pageNo: String, @Query("pageSize")pageSize: String):GrainListResponse

    /**
     * 增加订单
     */
    @POST("yhjbxx/loginAjax/")
    suspend fun addOrder()

    /**
     * 删除订单
     */
    @POST("yhjbxx/loginAjax/")
    suspend fun deleteOrder()

    /**
     * 修改订单
     */
    @POST("yhjbxx/loginAjax/")
    suspend fun modifyOrder()


}