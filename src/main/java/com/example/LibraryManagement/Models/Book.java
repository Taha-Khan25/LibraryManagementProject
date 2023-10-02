package com.example.LibraryManagement.Models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private Integer isbn;

    private String publishedYear;

    private String title;

    private Integer quantity;


    @CreatedDate
    private Date creationDate;

    @LastModifiedDate
    private Date updationDate;

    @DBRef
    private Author author;



}