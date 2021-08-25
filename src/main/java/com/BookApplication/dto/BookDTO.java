package com.BookApplication.dto;


import com.BookApplication.model.Author;
import com.BookApplication.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotBlank(message = "Book name cannot be null")
    private String bookName;
    @NotNull(message = "Book Price cannot be zero")
    private Long bookPrice;
    @NotNull(message = "Quantity cannot be zero")
    private Integer quantity;
    //private List<AuthorDTO> authors;
}
