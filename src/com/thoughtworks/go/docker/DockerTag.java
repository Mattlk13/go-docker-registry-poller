package com.thoughtworks.go.docker;

public class DockerTag {
    private String name;
    private String sha;

    public DockerTag(String name, String sha) {
        this.name = name;
        this.sha = sha;
    }

    public String getName() {
        return name;
    }

    public String getSha() {
        return sha;
    }
}
