package com.example.clientgithub.dataSource.authenticationSource;

import com.google.gson.annotations.SerializedName;

public class Code {
    String user_code;
    String verification_uri;
    String device_code;
    public Code(String user_code, String verification_uri, String device_code) {
        this.user_code = user_code;
        this.verification_uri = verification_uri;
        this.device_code = device_code;
    }

    public String getDevice_code() {return device_code;}

    public String getVerificationUri() {
        return verification_uri;
    }

    public String getUserCode() {
        return user_code;
    }
}
