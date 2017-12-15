package com.perusudroid.sampleretro.dto.request;

/**
 * Created by perusu on 13/12/17.
 */

public class PostInput {

    String name;
    String hobby;

    public PostInput(String name, String hobby) {
        this.name = name;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
