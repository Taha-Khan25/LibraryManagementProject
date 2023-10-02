package com.example.LibraryManagement.Services;


import com.example.LibraryManagement.DTO.InitiateTransactionRequest;
import com.example.LibraryManagement.Exceptions.HandleBookException;
import com.example.LibraryManagement.Exceptions.HandleTransactionException;
import com.example.LibraryManagement.Models.*;
import com.example.LibraryManagement.Repository.TransactionRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;

    @Autowired
    TransactionRepository transactionRepository;

    private final Integer duration=15;

    public void initiateTxn(InitiateTransactionRequest request) throws HandleTransactionException, HandleBookException {
        if (request.getTransactionType() == TransactionType.ISSUE) {
            borrowBook(request);
        } else {
            returnBook(request);
        }
    }

    private void returnBook(InitiateTransactionRequest request) throws HandleTransactionException, HandleBookException {
        String bookId = request.getBookId();
        String userId = request.getUserId();
        String adminId = request.getAdminId();

        Users user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);
        Admin admin = adminService.findAdminById(adminId);

        if (user == null || book == null || admin == null) {
            throw new HandleTransactionException("Invalid Request");
        }

        Transaction issuanceTxn = transactionRepository.
                findTopByUsersAndBookAndTransactionTypeOrderByIdDesc
                        (user, book, TransactionType.ISSUE);

        Integer fine = caclculateFine(issuanceTxn.getCreatedOn());

        if (issuanceTxn == null) {
            throw new HandleTransactionException("Invalid Request");
        }


        Transaction transaction = null;
        try {
            transaction = Transaction.builder()
                    .txnId(UUID.randomUUID().toString())
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .users(user)
                    .admin(admin)
                    .book(book)
                    .fine(fine)
                    .createdOn(LocalDate.now())
                    .build();

            transactionRepository.save(transaction);

            bookService.returnBook(user, book);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        } catch (HandleBookException e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            throw new HandleBookException(e.getMessage());
        } finally {
            transactionRepository.save(transaction);
        }
    }

    private Integer caclculateFine(LocalDate issuanceDate) {
        LocalDate currentDate = LocalDate.now();

        long daysPassed = ChronoUnit.DAYS.between(issuanceDate, currentDate);

        if (daysPassed > duration) {
            return (int) (daysPassed - duration);
        }
        return 0;
    }



    public void borrowBook(InitiateTransactionRequest request) throws HandleTransactionException, HandleBookException {
        String bookId = request.getBookId();
        String userId = request.getUserId();
        String adminId = request.getAdminId();

        Users user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);
        Admin admin = adminService.findAdminById(adminId);

        if (user == null || book == null || admin == null) {
            throw new HandleTransactionException("Invalid Request");
        }
        Transaction transaction = null;

        try {
            transaction = Transaction.builder()
                    .txnId(UUID.randomUUID().toString())
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .users(user)
                    .admin(admin)
                    .book(book)
                    .createdOn(LocalDate.now())
                    .build();

            transactionRepository.save(transaction);

            bookService.borrowBook(user, book);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        } catch (HandleBookException e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            throw new HandleBookException(e.getMessage());
        } finally {
            transactionRepository.save(transaction);
        }
    }

}
