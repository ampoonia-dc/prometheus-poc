package io.daalchini.prometheuspoc.routes;

import io.daalchini.prometheuspoc.data.BookDto;
import io.daalchini.prometheuspoc.service.BookService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BookRoute {

    private final BookService bookService;

    public BookRoute(BookService bookService) {
        this.bookService = bookService;
    }


    @Timed("book.get")
    @GetMapping("/books")
    public List<BookDto> ping() {
        return bookService.findAll();
    }
}
