package com.BookApplication.model;

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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "varchar(255) default 'Bharath'")
    private String authorFirstName;
    private String authorLastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String phoneNo;
    /*@ManyToMany(mappedBy = "authors")
    private List<Book> books;*/
    @CreationTimestamp
    private LocalDateTime createdTimeStamp;
    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;
    /*@ManyToOne(fetch =FetchType.LAZY, optional = false )
    @JoinColumn(name = "book_id",nullable = false)*/
   // private Book book;
}
