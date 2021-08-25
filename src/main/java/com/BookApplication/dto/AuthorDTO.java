package com.BookApplication.dto;

import com.BookApplication.model.Author;
import com.BookApplication.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    @NotNull(message = "Author first name cannot be blank")
    private String authorFirstName;
    @NotNull(message = "Author last name cannot be blank")
    private String authorLastName;
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Length(min =10,max = 10 ,message = "Phonenumber is not valid")
    private String phoneNo;
}
