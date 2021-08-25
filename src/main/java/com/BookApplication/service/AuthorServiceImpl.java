package com.BookApplication.service;
import com.BookApplication.dto.AuthorDTO;
import com.BookApplication.exception.AuthorNotFoundException;
import com.BookApplication.exception.BookException;
import com.BookApplication.exception.BookNotFoundException;
import com.BookApplication.model.Author;
import com.BookApplication.model.Book;
import com.BookApplication.repository.AuthorRepository;
import com.BookApplication.repository.BookRepository;
import com.BookApplication.utils.EmailUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    EmailUtil emailUtil;
    @Value("${com.author.create.emailSubject}")
    String email_subject;
    @Value("${com.author.create.emailBody}")
    String email_body;
    @Override
    public void addAuthor(Long id,AuthorDTO authorDTO) {
        Optional<Author> isAuthorPresent= authorRepository.findByEmail(authorDTO.getEmail());
        if(isAuthorPresent.isPresent())
        {
            throw new BookException(HttpStatus.CONFLICT.value(), "Author is already present!! please update the details");
        }
        Author author=new Author();
        BeanUtils.copyProperties(authorDTO,author);
        Author savedAuthor=authorRepository.save(author);
        Book book=bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.getAuthors().add(savedAuthor);
        bookRepository.save(book);
        emailUtil.sendEmail(savedAuthor.getEmail(),email_subject,email_body+""+"With Id "+savedAuthor.getId().toString());
    }
    @Override
    public void deleteAuthor(Long id) {
        Author author=authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
        authorRepository.delete(author);
    }
    @Override
    public Author getAuthorById(Long id) {
        Author author=new Author();
        author= authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
        return author;
    }
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void updateAuthors(Long id, AuthorDTO AuthorDTO) {
        Author author=authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
        author=updateAuthor(author,AuthorDTO);
       Author savedAuthor= authorRepository.save(author);
        emailUtil.sendEmail(savedAuthor.getEmail(),email_subject,email_body+""+"With Id "+savedAuthor.getId().toString());
    }

    public Author updateAuthor(Author actualAuthor, AuthorDTO AuthorDTO)
    {
        if(AuthorDTO.getAuthorFirstName()!=null)
            actualAuthor.setAuthorFirstName(AuthorDTO.getAuthorFirstName());
        if(AuthorDTO.getAuthorLastName()!=null)
            actualAuthor.setAuthorLastName(AuthorDTO.getAuthorLastName());
        if(AuthorDTO.getPhoneNo()!=null)
            actualAuthor.setPhoneNo(AuthorDTO.getPhoneNo());
        if(AuthorDTO.getEmail()!=null)
            actualAuthor.setEmail(AuthorDTO.getEmail());
        return actualAuthor;
    }
}
