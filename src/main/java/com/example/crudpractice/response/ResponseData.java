package com.example.crudpractice.response;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {

    private T data;

    private String status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
