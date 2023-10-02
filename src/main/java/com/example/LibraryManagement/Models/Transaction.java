package com.example.LibraryManagement.Models;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    private String txnId;

    private TransactionStatus transactionStatus;

    private TransactionType transactionType;

    @CreatedDate
    private LocalDate createdOn;

    @LastModifiedDate
    private Date updatedOn;

    private Integer fine;

    @DBRef
    private Book book;

    @DBRef
    private Users users;

    @DBRef
    private Admin admin;

}
