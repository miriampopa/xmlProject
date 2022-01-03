package com.library_project.model;

import java.util.List;

public class BookChaptersModel extends BookModel{

    private List<String> authors;
    private List<Chapter> chapters;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
