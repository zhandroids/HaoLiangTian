package com.hlt.hao.http;

/**
 * create by zhangzhuo
 * time: 2020/7/12
 * desc
 */
public class RetrofitClient {
    public static <T> T getApiService(Class<T> service) {
        return RetrofitUtil.Companion.getRetrofit().create(service);
    }
}
