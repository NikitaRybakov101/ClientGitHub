package com.example.clientgithub.dataSource.commitsSource;

import com.google.gson.annotations.SerializedName;

public class Commit {

    String name;

    String date;

    String message;

    String sha;

    public Commit(String name, String date, String message, String sha) {
        this.name = name;
        this.date = date;
        this.message = message;
        this.sha = sha;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getSha() {
        return sha;
    }
}
