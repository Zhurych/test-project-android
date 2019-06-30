package com.example.t3testapp;

/**
 * Класс для отображения списка
 */
public class UserData {
    private String photoId;
    private String name;
    private int state;

    public UserData(String name) {
        this.name = name;
        photoId = "";
        state = -1;
    }

    public UserData(String photoId, String name, int state) {
        this.photoId = photoId;
        this.name = name;
        this.state = state;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getName() {
        return name;
    }

    public int getState() {
        return state;
    }
}
