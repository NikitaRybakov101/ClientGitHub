package com.example.clientgithub.dataSource.commitsSource;

import com.google.gson.annotations.SerializedName;

public class ResponseCommitAuthorDto {

    @SerializedName("name")
    String name;

    @SerializedName("date")
    String date;


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
