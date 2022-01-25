package com.library_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library_project.model.*;
import com.library_project.services.AuthorService;
import com.library_project.services.BookService;
import com.library_project.services.UserService;
import org.apache.camel.json.simple.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    UserService userService;
    BookService bookService;
    AuthorService authorService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService){this.authorService = authorService;}

    // *********** user requests ***********
    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<User> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);

    }


    // *********** book requests ***********
    @PostMapping("/api/search-book")
    public ResponseEntity<?> getSearchResultViaAjax1Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<Book> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<Book> books = bookService.findBookByName(search.getBookTitle());
        if (books.isEmpty()) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(books);

        return ResponseEntity.ok(result);

    }


    @PostMapping("/api/search-book-titles-that-start-with")
    public ResponseEntity<?> getSearchResultViaAjax2Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<BookChaptersModel> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<BookChaptersModel> books = bookService.getBookThatStartWith(search.getBookTitlesThatStartWith());
        if (books.isEmpty()) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(books);

        return ResponseEntity.ok(result);

    }


    // *********** author requests ***********
    @PostMapping("/api/search-authors")
    public ResponseEntity<?> getSearchResultViaAjax5Response(@Valid @RequestBody SearchCriteria search, Errors errors)  {

        AjaxResponseBody<Author> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        Set<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(authors.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }

    //e.g year: 2010
    @PostMapping("/api/search-authors-filtered-by-copyrightYear")
    public ResponseEntity<?> getSearchResultViaAjax3Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<BookAuthorsFilteredByCopyRightYear> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        Set<BookAuthorsFilteredByCopyRightYear> books = authorService.getBookAuthorsFilteredByCopyRightYear(Integer.parseInt(search.getAuthorsFilteredByCopyrightYear()));
        if (books.isEmpty()) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(books.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }

    //e.g {"publisher": "Wiley-IEEE Press","authorName": "Richard"}
    @PostMapping("/api/search-authors-filtered-by-publisher-and-name")
    public ResponseEntity<?> getSearchResultViaAjax4Response(@Valid @RequestBody SearchCriteria search, Errors errors)  {

        AjaxResponseBody<BookAuthorsFilteredByPublisher> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Request1Ajax r1Ajax = null;
        try {
            r1Ajax = objectMapper.readValue(search.getAuthorsFilteredByPublisherAndName(), Request1Ajax.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<BookAuthorsFilteredByPublisher> authors = authorService.getBookAuthorsFilteredByPublisherAndAuthorName(r1Ajax.getPublisher(), r1Ajax.getAuthorName());
        if (authors.isEmpty()) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(authors.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }

    //e.g {"title": "Cele cinci limbaje ale iubirii","userId": "user_3"}
    // *********** booked book requests ***********
    @PostMapping("/api/add-booked-book")
    public ResponseEntity<?> getSearchResultViaAjax6Response(@Valid @RequestBody SearchCriteria search, Errors errors)  {

        AjaxResponseBody<BookedBook> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        BookedBook bookedBook = null;
        try {
            bookedBook = objectMapper.readValue(search.getBookedBook(), BookedBook.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookService.addBookedBook(bookedBook);

        List<BookedBook> bookedBooks = new ArrayList<>();
        bookedBooks.add(bookedBook);

        if (bookedBooks.isEmpty()) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(bookedBooks.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }


    // *********** booked book requests ***********
    @PostMapping("/api/delete-booked-book")
    public ResponseEntity<?> getSearchResultViaAjax7Response(@Valid @RequestBody SearchCriteria search, Errors errors)  {

        AjaxResponseBody<BookedBook> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        BookedBook bookedBook = null;
        try {
            bookedBook = objectMapper.readValue(search.getDeletedBookedBook(), BookedBook.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookService.deleteBookedBook(bookedBook);

        List<BookedBook> bookedBooks = new ArrayList<>();

        if (bookedBooks.isEmpty()) {
            result.setMsg("success!");
        } else {
            result.setMsg("The book was not deleted");
        }

        bookedBooks.remove(bookedBook);


        result.setResult(bookedBooks.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }

}
