package com.BookApplication.service;

import com.BookApplication.constants.BookStatus;
import com.BookApplication.dto.BookDTO;
import com.BookApplication.exception.BookException;
import com.BookApplication.exception.BookNotFoundException;
import com.BookApplication.model.Author;
import com.BookApplication.model.Book;
import com.BookApplication.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        book.setStatus(BookStatus.PUBLISHED);
        System.out.println(book);
        System.out.println(book.getStatus());
        bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long id) {
        Book book= bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setStatus(BookStatus.HELD);
        bookRepository.save(book);
    }
    @Override
    public Page<Book> getAllBooks() {
        Pageable paging = PageRequest.of(0, 2);
        return bookRepository.findAll(paging);
    }

    @Override
    public Book getBookByName(Long id) {
        return bookRepository.findById(id).orElseThrow
                (BookNotFoundException::new);
    }

    @Override
    public void updateBookQty(Long id, Integer qty) {
        Book book= bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setQuantity(qty);
        bookRepository.save(book);
    }

    @Override
    public void updateBookPrice(Long id, Long price) {
        Book book= bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setBookPrice(price);
        bookRepository.save(book);
    }

    @Override
    public void updateBookAuthors(Long id, List<Author> authors) {
        Book book= bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setAuthors(authors);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long Id,BookDTO book) {
        Book actualBook= bookRepository.findById(Id).orElseThrow(BookNotFoundException::new);
        actualBook=updateBookDetails(actualBook,book);
        System.out.println("ID is "+Id);
        System.out.println("After updating value is "+actualBook);
        bookRepository.save(actualBook);
    }

    public Book updateBookDetails(Book actualBook, BookDTO updatedBook)
    {
        if(updatedBook.getBookName()!=null)
            actualBook.setBookName(updatedBook.getBookName());
        if(updatedBook.getBookPrice()!=null)
            actualBook.setBookPrice(updatedBook.getBookPrice());
        if(updatedBook.getQuantity()!=0)
            actualBook.setQuantity(updatedBook.getQuantity());
        return actualBook;
    }
}
