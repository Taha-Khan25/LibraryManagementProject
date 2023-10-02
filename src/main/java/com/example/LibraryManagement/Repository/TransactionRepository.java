package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Models.Transaction;

import com.example.LibraryManagement.Models.TransactionType;
import com.example.LibraryManagement.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Transaction findTopByUsersAndBookAndTransactionTypeOrderByIdDesc(Users user, Book book, TransactionType type);
}
