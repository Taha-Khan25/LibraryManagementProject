package com.example.LibraryManagement.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "admin")
public class Admin {

    @Id
    private String adminId;

    private String name;

    private String email;

    @CreatedDate
    private Date createdOn;


}