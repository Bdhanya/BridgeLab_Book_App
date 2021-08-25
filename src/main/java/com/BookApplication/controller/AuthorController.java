package com.BookApplication.controller;

import com.BookApplication.configuration.ApplicationConfig;
import com.BookApplication.dto.AuthorDTO;

import com.BookApplication.dto.BookDTO;
import com.BookApplication.response.Response;
import com.BookApplication.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/{id}")
    public ResponseEntity<Response> addBook(@PathVariable Long id,@Valid @RequestBody AuthorDTO authorDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), ""),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        authorService.addAuthor(id,authorDTO);
        return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), ApplicationConfig.getMessageAccessor().getMessage("110"), ""), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response>deleteAuthor(@PathVariable Long id)
    {
        authorService.deleteAuthor(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("109"), ""), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response>getAuthorById(@PathVariable Long id){
        authorService.getAuthorById(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("111"), ""), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Response>getAuthors(){
        authorService.getAllAuthors();
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("112"), ""), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Response>updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDTO authorDTO, BindingResult bindingResult){
        authorService.updateAuthors(id,authorDTO);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("112"), ""), HttpStatus.OK);
    }
}
