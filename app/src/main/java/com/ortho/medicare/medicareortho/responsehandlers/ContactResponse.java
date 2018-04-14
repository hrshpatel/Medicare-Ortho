package com.ortho.medicare.medicareortho.responsehandlers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Harsh on 03-04-2018.
 */

public class ContactResponse {

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
        @SerializedName("dribble_link")
        private String dribbleLink;
        @SerializedName("whatsup_link")
        private String whatsupLink;
        @SerializedName("skype_link")
        private String skypeLink;
        @SerializedName("linkedin_link")
        private String linkedinLink;
        @SerializedName("pineret_link")
        private String pineretLink;
        @SerializedName("facebook_link")
        private String facebookLink;
        @SerializedName("contact_us_logo")
        private String contactUsLogo;
        @SerializedName("header_logo")
        private String headerLogo;
        @SerializedName("website_link")
        private String websiteLink;
        @SerializedName("email_id")
        private String emailId;
        @SerializedName("mobile_number")
        private String mobileNumber;
        @SerializedName("phone_number")
        private String phoneNumber;
        @SerializedName("office_address")
        private String officeAddress;
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

        public String getDribbleLink() {
            return dribbleLink;
        }

        public void setDribbleLink(String dribbleLink) {
            this.dribbleLink = dribbleLink;
        }

        public String getWhatsupLink() {
            return whatsupLink;
        }

        public void setWhatsupLink(String whatsupLink) {
            this.whatsupLink = whatsupLink;
        }

        public String getSkypeLink() {
            return skypeLink;
        }

        public void setSkypeLink(String skypeLink) {
            this.skypeLink = skypeLink;
        }

        public String getLinkedinLink() {
            return linkedinLink;
        }

        public void setLinkedinLink(String linkedinLink) {
            this.linkedinLink = linkedinLink;
        }

        public String getPineretLink() {
            return pineretLink;
        }

        public void setPineretLink(String pineretLink) {
            this.pineretLink = pineretLink;
        }

        public String getFacebookLink() {
            return facebookLink;
        }

        public void setFacebookLink(String facebookLink) {
            this.facebookLink = facebookLink;
        }

        public String getContactUsLogo() {
            return contactUsLogo;
        }

        public void setContactUsLogo(String contactUsLogo) {
            this.contactUsLogo = contactUsLogo;
        }

        public String getHeaderLogo() {
            return headerLogo;
        }

        public void setHeaderLogo(String headerLogo) {
            this.headerLogo = headerLogo;
        }

        public String getWebsiteLink() {
            return websiteLink;
        }

        public void setWebsiteLink(String websiteLink) {
            this.websiteLink = websiteLink;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getOfficeAddress() {
            return officeAddress;
        }

        public void setOfficeAddress(String officeAddress) {
            this.officeAddress = officeAddress;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
