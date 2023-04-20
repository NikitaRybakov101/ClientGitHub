package com.example.clientgithub.dataSource.commitsSource;

import com.google.gson.annotations.SerializedName;

public class ResponseCommitDataDto {
    @SerializedName("message")
    String message;

    @SerializedName("author")
    ResponseCommitAuthorDto author;

    public String getMessage() {
        return message;
    }

    public ResponseCommitAuthorDto getAuthor() {
        return author;
    }

}
