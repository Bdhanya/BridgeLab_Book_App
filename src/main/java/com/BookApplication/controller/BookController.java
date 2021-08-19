package com.BookApplication.controller;

import com.BookApplication.configuration.ApplicationConfig;
import com.BookApplication.dto.BookDTO;
import com.BookApplication.model.Book;
import com.BookApplication.response.Response;
import com.BookApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    /*@PostMapping("/addBook")
    public void addBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors())
            bookService.addBook(bookDTO);
    }*/
    @PostMapping("/addBook")
    public ResponseEntity<Response> addBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        bookService.addBook(bookDTO);
        return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), ApplicationConfig.getMessageAccessor().getMessage("100"), ""), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBook/{bookName}")
    public ResponseEntity<Response>deleteBook(@PathVariable String bookName)
    {
        bookService.deleteBook(bookName);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("101"), ""), HttpStatus.OK);
    }
    @GetMapping("/getBooks")
    public ResponseEntity<Response> getAllBook()
    {
        List<Book> books=bookService.getAllBooks();
        books.forEach(System.out::println);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("103"), ""), HttpStatus.OK);
    }
    @GetMapping("/{bookName}")
    public ResponseEntity<Response>getBookByName(@PathVariable String bookName)
    {
        bookService.getBookByName(bookName);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("104"), ""), HttpStatus.OK);
    }

}
