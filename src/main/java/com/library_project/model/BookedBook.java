package com.library_project.model;

public class BookedBook {
    private String title;
    private String userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookedBook{" +
                "title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
