package com.BookApplication.service;

import com.BookApplication.dto.BookDTO;
import com.BookApplication.exception.BookException;
import com.BookApplication.exception.BookNotFoundException;
import com.BookApplication.model.Book;
import com.BookApplication.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(BookDTO bookDTO) {
       Optional<Book> isBookPresent= bookRepository.findByBookName(bookDTO.getBookName());
       if(isBookPresent.isPresent())
       {
           throw new BookException(HttpStatus.CONFLICT.value(), "Book is already present please update the quantity");
       }
        Book book=new Book();
        BeanUtils.copyProperties(bookDTO,book);
        bookRepository.save(book);
    }
    @Override
    public void deleteBook(String bookName) {
        Optional<Book> isBookPresent= bookRepository.findByBookName(bookName);
        if(isBookPresent.isPresent())
            bookRepository.delete(isBookPresent.get());
        else
            throw new BookNotFoundException();

    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByName(String bookName) {
        return bookRepository.findByBookName(bookName).orElseThrow
                (BookNotFoundException::new);
    }
}
