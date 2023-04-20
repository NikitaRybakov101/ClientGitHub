package com.example.clientgithub.dataSource.commitsSource;

import com.google.gson.annotations.SerializedName;

public class ResponseCommitDto {

    @SerializedName("sha")
    String sha;

    @SerializedName("commit")
    ResponseCommitDataDto commit;

    public String getSha() {
        return sha;
    }

    public ResponseCommitDataDto getCommit() {
        return commit;
    }
}
