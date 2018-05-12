package com.ortho.medicare.medicareortho.responsehandlers;

import com.google.gson.annotations.SerializedName;
import com.ortho.medicare.medicareortho.models.CategoryModel;

import java.util.List;

public class CategoryListResponse {

    @SerializedName("data")
    private List<CategoryModel> data;
    @SerializedName("msg")
    private String msg;
    @SerializedName("response")
    private String response;

    public List<CategoryModel> getData() {
        return data;
    }

    public void setData(List<CategoryModel> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
