package com.BookApplication.controller;

import com.BookApplication.configuration.ApplicationConfig;
import com.BookApplication.dto.BookDTO;
import com.BookApplication.model.Book;
import com.BookApplication.response.Response;
import com.BookApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    @PostMapping("/")
    public ResponseEntity<Response> addBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        bookService.addBook(bookDTO);
        return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), ApplicationConfig.getMessageAccessor().getMessage("100"), ""), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response>deleteBook(@PathVariable Long id)
    {
        bookService.deleteBook(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("101"), ""), HttpStatus.OK);
    }
    @GetMapping("/getBooks")
    public ResponseEntity<Response> getAllBook()
    {
        Page<Book> books=bookService.getAllBooks();
        books.forEach(System.out::println);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("103"), ""), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response>getBookByName(@PathVariable Long id)
    {
        bookService.getBookByName(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("104"), ""), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response>updateBook(@PathVariable Long id,@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        bookService.updateBook(id,bookDTO);
        return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), ApplicationConfig.getMessageAccessor().getMessage("107"), ""), HttpStatus.CREATED);
    }

    /*@PutMapping("update/{id}")
    public ResponseEntity<Response>updateBook(@PathVariable Long id, @RequestBody Book book)
    {
        bookService.updateBook(id,book);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("105"), ""), HttpStatus.OK);
    }*/

}
