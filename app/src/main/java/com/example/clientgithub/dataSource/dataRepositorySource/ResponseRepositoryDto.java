package com.example.clientgithub.dataSource.dataRepositorySource;

import com.google.gson.annotations.SerializedName;

public class ResponseRepositoryDto {

    @SerializedName("name")
    String nameRepository;
    @SerializedName("full_name")
    String fullName;
    @SerializedName("owner")
    ResponseOwnerDto owner;
    @SerializedName("description")
    String description;
    @SerializedName("forks_count")
    String forks_count;
    @SerializedName("watchers_count")
    String watchers_count;
    @SerializedName("visibility")
    String visibility;

    public String getWatchers_count() {
        return watchers_count;
    }

    public String getDescription() {
        return description;
    }

    public String getForks_count() {
        return forks_count;
    }

    public String getVisibility() {
        return visibility;
    }

    public ResponseOwnerDto getOwner() {
        return owner;
    }

    public String getNameRepository() {
        return nameRepository;
    }

    public String getFullName() {
        return fullName;
    }
}
