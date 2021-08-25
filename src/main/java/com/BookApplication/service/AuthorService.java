package com.BookApplication.service;

import com.BookApplication.dto.AuthorDTO;
import com.BookApplication.model.Author;

import java.util.List;

public interface AuthorService {

    public void addAuthor(Long id,AuthorDTO authorDTO);
    public void deleteAuthor(Long id);
    public Author getAuthorById(Long id);
    public List<Author> getAllAuthors();
    public void updateAuthors(Long id, AuthorDTO authorDTO);
}
