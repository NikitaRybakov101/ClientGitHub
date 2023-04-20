package com.example.clientgithub.dataSource.dataUserSource;

public class User {
    String avatar_url;
    String login;

    public User(String avatar_url, String login) {
        this.avatar_url = avatar_url;
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }
}
