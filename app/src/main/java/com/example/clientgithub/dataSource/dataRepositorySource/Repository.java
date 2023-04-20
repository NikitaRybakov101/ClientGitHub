package com.example.clientgithub.dataSource.dataRepositorySource;

public class Repository {

    String nameRepository;

    String fullName;
    Owner owner;

    String description;

    String forks_count;

    String watchers_count;

    String visibility;

    public Repository(String name, String full_name, Owner owner, String description, String forks_count, String watchers_count, String visibility) {
        this.nameRepository = name;
        this.fullName = full_name;
        this.owner = owner;
        this.description = description;
        this.forks_count = forks_count;
        this.watchers_count = watchers_count;
        this.visibility = visibility;
    }

    public String getNameRepository() {
        return nameRepository;
    }

    public String getFullName() {
        return fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public String getForks_count() {
        return forks_count;
    }

    public String getWatchers_count() {
        return watchers_count;
    }

    public String getVisibility() {
        return visibility;
    }

}
