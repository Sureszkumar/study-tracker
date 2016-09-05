package com.sample.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sample.domain.Book;
import com.sample.repository.BookRepository;

@Service
@Validated
public class BookService extends BaseService<Book> {

    @Inject
    private BookRepository userRepository;

    @PostConstruct
    protected void init() {
        super.init(userRepository);
    }


}
