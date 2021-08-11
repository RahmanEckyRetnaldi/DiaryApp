package com.diary.project.models;

public class ModelDiary {

    private String title;
    private String description;
    private String date;
    private String userId;

    public ModelDiary() {
    }

    public ModelDiary(String title, String description, String date, String userId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
