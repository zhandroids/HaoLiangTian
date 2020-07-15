package com.hlt.hao.http;

/**
 * create by zhangzhuo
 * time: 2020/7/13
 * desc
 */
public class LiveDataResponse<T> {
    private boolean state;
    private T data;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
