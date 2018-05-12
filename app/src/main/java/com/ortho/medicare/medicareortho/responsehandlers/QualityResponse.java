package com.ortho.medicare.medicareortho.responsehandlers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Harsh on 12-05-2018.
 */

public class QualityResponse {

    @SerializedName("data")
    private Data data;
    @SerializedName("msg")
    private String msg;
    @SerializedName("response")
    private String response;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    public static class Data {
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("description")
        private String description;
        @SerializedName("web_url")
        private String document;
        @SerializedName("sub_title")
        private String subTitle;
        @SerializedName("title")
        private String title;
        @SerializedName("id")
        private int id;

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
