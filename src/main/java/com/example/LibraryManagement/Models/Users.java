package com.example.LibraryManagement.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class Users {

    @Id
    private String id;
    private String name;


    private String email;

    private SecuredUser securedUser;

    @DBRef
    private List<Book> borrowedBooks;

}
