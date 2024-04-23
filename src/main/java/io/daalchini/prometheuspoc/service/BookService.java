package io.daalchini.prometheuspoc.service;

import io.daalchini.prometheuspoc.data.BookDto;
import io.daalchini.prometheuspoc.data.BookMapper;
import io.daalchini.prometheuspoc.data.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(
            BookRepository bookRepository,
            BookMapper bookMapper
    ) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> findAll() {
        var all = bookRepository.findAll();
        log.debug("Found {} books", all.size());

        return bookMapper.toDto(all);
    }

    public BookDto findById(Long bookId) {
        var byId = bookRepository.findById(bookId);

        return byId.map(bookMapper::toDto).orElse(null);
    }
}
