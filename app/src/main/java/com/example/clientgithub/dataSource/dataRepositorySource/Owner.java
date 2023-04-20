package com.example.clientgithub.dataSource.dataRepositorySource;

public class Owner {

    String avatar_url;
    String login;

    public Owner(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }
}
