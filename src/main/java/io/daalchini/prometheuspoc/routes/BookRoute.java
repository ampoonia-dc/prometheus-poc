package io.daalchini.prometheuspoc.routes;

import io.daalchini.prometheuspoc.data.BookDto;
import io.daalchini.prometheuspoc.service.BookService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.MeterTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BookRoute {

    private final BookService bookService;

    public BookRoute(BookService bookService) {
        this.bookService = bookService;
    }


    @Timed(value = "book.get_all", extraTags = {"fetch_type", "all"})
    @GetMapping("/books")
    public List<BookDto> findBooks() {
        return bookService.findAll();
    }

    @Timed(value = "book.get_by_id", extraTags = {"fetch_type", "one"})
    @GetMapping("/books/{bookId}")
    public BookDto findBookById(
            @MeterTag("book_id")
            @PathVariable("bookId") Long bookId
    ) {
        return bookService.findById(bookId);
    }
}
