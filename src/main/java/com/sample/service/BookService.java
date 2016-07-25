package com.sample.service;

import com.sample.domain.Book;
import com.sample.domain.Book;
import com.sample.domain.User;
import com.sample.repository.BookRepository;
import com.sample.repository.BookRepository;
import com.sample.service.exception.ServiceException;
import com.sample.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
