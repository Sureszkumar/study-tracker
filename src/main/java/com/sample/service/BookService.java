package com.sample.service;

import com.sample.domain.Book;
import com.sample.domain.Book;
import com.sample.repository.BookRepository;
import com.sample.repository.BookRepository;
import com.sample.service.exception.ServiceException;
import com.sample.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private final BookRepository repository;

    @Inject
    public BookService(final BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Book create(@NotNull final Book book) {
        LOGGER.debug("Creating {}", book);
        book.setCreationDateTime(LocalDateTime.now());
        book.setLastChangeTimestamp(LocalDateTime.now());
        return repository.save(book);
    }

    @Transactional
    public Book update(@NotNull final Book book) {
        LOGGER.debug("Updating {}", book);
        book.setLastChangeTimestamp(LocalDateTime.now());
        return repository.save(book);
    }

    @Transactional
    public void delete(Long bookId) {
        LOGGER.debug("Deleting {}", bookId);
        Book existing = repository.findOne(bookId);
        if (existing == null) {
            throw new ServiceException(
                    String.format("Book with id =%s not exist", bookId));
        }
        repository.delete(bookId);
    }

    @Transactional(readOnly = true)
    public Book get(Long bookId) {
        LOGGER.debug("Retrieving the Book : {}", bookId);
        Book Book = repository.findOne(bookId);
        if (Book == null) {
            throw new ServiceException(
                    String.format("Book with id=%s is not exist", bookId));
        }
        return Book;
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        LOGGER.debug("Retrieving the list of all Books");
        return repository.findAll();
    }


}
