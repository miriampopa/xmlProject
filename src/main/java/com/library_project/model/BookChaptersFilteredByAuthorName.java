package com.library_project.model;

public class BookChaptersFilteredByAuthorName extends BookChaptersModel{
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
