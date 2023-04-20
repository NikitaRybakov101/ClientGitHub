package com.example.clientgithub.dataSource.authenticationSource;

import com.google.gson.annotations.SerializedName;

public class ResponseCodeDto {
    @SerializedName("user_code")
    String user_code;

    @SerializedName("verification_uri")
    String verification_uri;

    @SerializedName("device_code")
    String device_code;
    public String getVerification_uri() {
        return verification_uri;
    }
    public String getUser_code() {
        return user_code;
    }

    public String getDevice_code() {
        return device_code;
    }
}
