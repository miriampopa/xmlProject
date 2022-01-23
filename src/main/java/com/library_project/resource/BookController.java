package com.library_project.resource;

import com.library_project.model.*;
import com.library_project.services.BookService;
import com.library_project.utils.DataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/book")
@Api(value = "Book Resource", description = "shows books info")
public class BookController {

    @Autowired
    BookService bookService;

    @ApiOperation(value = "Returns book list")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful request")
            }
    )
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBookList();
    }

    //e.g word: A
    @ApiOperation(value = "Returns books that have title that start with specified word")
    @GetMapping("/booksTitleStartWith/{word}")
    public List<BookChaptersModel> getBookThatStartWith(@PathVariable("word") final String word) {
        return bookService.getBookThatStartWith(word);
    }

    @ApiOperation(value = "Returns books for author that has coworkers")
    @GetMapping("/booksWithAuthorThatHasCoworkers")
    public List<Book> getBookWithAuthorThatHasCoworkers() {
        return bookService.getBooksWithAuthorThatHasCoworkers();
    }

    //e.g authorName: Jeff Richard Keller
    @ApiOperation(value = "Returns chapters by author name")
    @GetMapping("/chaptersByAuthorName/{authorName}")
    public List<BookChaptersFilteredByAuthorName> getChaptersByAuthorName(@PathVariable("authorName") final String authorName) {
        return bookService.getChaptersByAuthorName(authorName);
    }

    //e.g bookTitle: Cele cinci limbaje ale iubirii
    @ApiOperation(value = "Returns number of sections filtered by book title")
    @GetMapping("/sectionsByBookTitle/{bookTitle}")
    public List<BookSectionsModel> getNumberOfSectionsForABookTitle(@PathVariable("bookTitle") final String bookTitle) {
        return bookService.getNumberOfSectionsForABookTitle(bookTitle);
    }


    @ApiOperation(value = "Returns number of sections filtered by book title")
    @GetMapping("/bookedBooks")
    public List<BookedBook> getAllBookedBooks() {
        return bookService.getAllBookedBooks();
    }


    @ApiOperation(value = "Booked a book")
    @PostMapping("/bookedBook")
    public BookedBook create(@RequestBody BookedBook book) {
        bookService.addBook(book);
        return book;
    }


    @ApiOperation(value = "Delete booked book by title")
    @DeleteMapping("/bookedBook/{bookTitle}")
    public void delete(@PathVariable("bookTitle") final String bookTitle) {
        bookService.delete(bookTitle);
    }

    @ApiOperation(value = "Delete booked book by title and userId")
    @DeleteMapping("/delete-bookedBook")
    public void deleteBookedBook(@RequestBody BookedBook book) {
        bookService.deleteBookedBook(book);
    }

    // should be removed
    @ApiOperation(value = "Returns the created book")
    @PostMapping("/createBook")
    public String createBook(@RequestBody final String hello) {
        return hello;
    }

    @ApiOperation(value = "Returns Hello World")
    @PutMapping("/put")
    public String helloPut(@RequestBody final String hello) {
        return hello;
    }
}


