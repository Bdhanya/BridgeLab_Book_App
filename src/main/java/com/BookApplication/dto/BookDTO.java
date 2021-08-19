package com.BookApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotBlank(message = "Book name cannot be null")
    private String bookName;
    @NotBlank(message = "Author name cannot be blank")
    private String authorName;
    @NotNull(message = "Book Price cannot be zero")
    private Long bookPrice;
    @NotNull(message = "Quantity cannot be zero")
    private Integer quantity;
}
