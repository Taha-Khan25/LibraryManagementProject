package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Models.TransactionType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InitiateTransactionRequest {

    private String userId;


    private String adminId;

    private String bookId;


    private TransactionType transactionType;
}