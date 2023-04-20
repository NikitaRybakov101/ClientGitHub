package com.example.clientgithub.dataSource.dataUserSource;

import com.google.gson.annotations.SerializedName;

public class ResponseUserDto {

    @SerializedName("avatar_url")
    String avatar_url;

    @SerializedName("login")
    String login;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }
}
