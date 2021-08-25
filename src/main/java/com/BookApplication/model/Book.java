package com.BookApplication.model;

import com.BookApplication.constants.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true,name = "BOOK_NAME")
    private String bookName;
    @Column(nullable = false,length = 5)
    private Long bookPrice;
    private int quantity;
    @CreationTimestamp
    private LocalDateTime createdTimeStamp;
    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;
    @Column(name = "BOOK_STATUS")
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    @OneToMany(targetEntity = Author.class)
    @JoinColumn(name = "book_id")
    private List<Author>authors;
    /*@ManyToMany
    @JoinTable(name = "Book_Author",joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns =
    @JoinColumn(name="author_id"))
    private List<Author> authors;*/

}
