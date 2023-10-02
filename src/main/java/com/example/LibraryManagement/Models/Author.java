package com.example.LibraryManagement.Models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "authors")
public class Author {
    @Id
    private String id;

    private String name;

    private String email;

    @CreatedDate
    private Date createdOn;

    private List<Book> bookList;


}