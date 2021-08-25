package com.BookApplication.service;

import com.BookApplication.dto.BookDTO;
import com.BookApplication.model.Author;
import com.BookApplication.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BookService {
    public void addBook(BookDTO bookDTO);
    public void deleteBook(Long id);
    public Page<Book> getAllBooks();
    public Book getBookByName(Long  id);
    public void updateBookQty(Long id,Integer qty);
    public void updateBookPrice(Long id,Long price);
    public void updateBookAuthors(Long id, List<Author>authors);
    public void updateBook(Long Id,BookDTO book);
}
