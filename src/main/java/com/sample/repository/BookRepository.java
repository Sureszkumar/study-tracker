package com.sample.repository;

import com.sample.domain.Book;
import com.sample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
