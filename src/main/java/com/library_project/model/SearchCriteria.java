package com.library_project.model;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

    //@NotBlank(message = "username can't be empty!")
    //users
    String username;

    //books
    String bookTitle;
    String bookTitlesThatStartWith;

    //authors
    String authors;
    String authorsFilteredByCopyrightYear;
    String authorsFilteredByPublisherAndName;

    String bookedBook;
    String deletedBookedBook;

    public String getUsername() {
        return username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBookTitle(String bookTitle){ this.bookTitle = bookTitle;}

    public String getBookTitlesThatStartWith() {
        return bookTitlesThatStartWith;
    }

    public void setBookTitlesThatStartWith(String bookTitlesThatStartWith) {
        this.bookTitlesThatStartWith = bookTitlesThatStartWith;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAuthorsFilteredByCopyrightYear() {
        return authorsFilteredByCopyrightYear;
    }

    public void setAuthorsFilteredByCopyrightYear(String authorsFilteredByCopyrightYear) {
        this.authorsFilteredByCopyrightYear = authorsFilteredByCopyrightYear;
    }

    public String getAuthorsFilteredByPublisherAndName() {
        return authorsFilteredByPublisherAndName;
    }

    public void setAuthorsFilteredByPublisherAndName(String authorsFilteredByPublisherAndName) {
        this.authorsFilteredByPublisherAndName = authorsFilteredByPublisherAndName;
    }

    public String getBookedBook() {
        return bookedBook;
    }

    public void setBookedBook(String bookedBook) {
        this.bookedBook = bookedBook;
    }

    public String getDeletedBookedBook() {
        return deletedBookedBook;
    }

    public void setDeletedBookedBook(String deletedBookedBook) {
        this.deletedBookedBook = deletedBookedBook;
    }
}