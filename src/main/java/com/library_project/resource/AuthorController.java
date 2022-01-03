package com.library_project.resource;

import com.library_project.model.Author;
import com.library_project.model.Book;
import com.library_project.model.BookAuthorsFilteredByCopyRightYear;
import com.library_project.model.BookAuthorsFilteredByPublisher;
import com.library_project.services.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/rest/author")
@Api(value = "Author Resource", description = "shows authors info")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @ApiOperation(value = "Returns authors list")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful request")
            }
    )
    @GetMapping("/authors")
    public Set<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    //e.g authorName: Mark Manson
    @ApiOperation(value = "Returns author that has the name specified as parameter")
    @GetMapping("/authorsByName/{authorNameName}")
    public Author getAuthorByName(@PathVariable("authorNameName") final String authorName) {
        return authorService.findAuthorsByName(authorName);
    }

    //e.g year: 2010
    @ApiOperation(value = "Returns authors that not published on specified year")
    @GetMapping("/authorsFilteredByCopyrightYear/{year}")
    public Set<BookAuthorsFilteredByCopyRightYear> authorsFilteredByCopyrightYear(@PathVariable("year") final String year) {
        return authorService.getBookAuthorsFilteredByCopyRightYear(Integer.parseInt(year));
    }

    //e.g publisher: Wiley-IEEE Press and word:Richard
    @ApiOperation(value = "Returns authors by publisher and name author should contains a specific word")
    @GetMapping("/authors/publisher/{publisher}/authorNameContains/{word}")
    public Set<BookAuthorsFilteredByPublisher> authorsFilteredByCopyrightYear(
            @PathVariable("publisher") final String publisher,
            @PathVariable("word") final String word) {
        return authorService.getBookAuthorsFilteredByPublisherAndAuthorName(publisher, word);
    }
}
