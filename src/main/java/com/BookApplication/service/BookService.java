package com.BookApplication.service;

import com.BookApplication.dto.BookDTO;
import com.BookApplication.model.Book;

import java.util.List;


public interface BookService {
    public void addBook(BookDTO bookDTO);
    public void deleteBook(String bookName);
    public List<Book> getAllBooks();
    public Book getBookByName(String bookName);
}
