package com.library_project.model;

import java.util.List;

public class BookAuthorModel {

    private List<Author> author;
    private String Title;

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
