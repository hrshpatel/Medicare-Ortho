package com.ortho.medicare.medicareortho.responsehandlers;

import com.google.gson.annotations.SerializedName;
import com.ortho.medicare.medicareortho.models.ProductDetailsModel;

import java.util.List;

/**
 * Created by Harsh on 15-04-2018.
 */

public class CategoryDetailsResponse {

    @SerializedName("data")
    private List<ProductDetailsModel> data;
    @SerializedName("msg")
    private String msg;
    @SerializedName("response")
    private String response;

    public List<ProductDetailsModel> getData() {
        return data;
    }

    public void setData(List<ProductDetailsModel> data) {
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
