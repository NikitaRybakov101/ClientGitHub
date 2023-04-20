package com.example.clientgithub.dataSource.authenticationSource;

import com.google.gson.annotations.SerializedName;

public class Token {
    String access_token;
    String token_type;

    public Token(String access_token, String token_type) {
        this.access_token = access_token;
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

}
