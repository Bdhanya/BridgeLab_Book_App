package com.BookApplication.repository;

import com.BookApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
public Optional<Book>findByBookName(String bookName);
}
