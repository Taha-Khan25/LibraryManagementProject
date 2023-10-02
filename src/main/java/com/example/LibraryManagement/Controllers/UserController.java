package com.example.LibraryManagement.Controllers;


import com.example.LibraryManagement.DTO.CreateUserRequest;
import com.example.LibraryManagement.DTO.InitiateTransactionRequest;
import com.example.LibraryManagement.DTO.ReturnBookRequest;
import com.example.LibraryManagement.Exceptions.HandleTransactionException;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Models.TransactionType;
import com.example.LibraryManagement.Models.Users;
import com.example.LibraryManagement.Exceptions.HandleBookException;
import com.example.LibraryManagement.Services.BookService;
import com.example.LibraryManagement.Services.TransactionService;
import com.example.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/create/user")
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createOrUpdateUser(createUserRequest.to());
    }


    @PostMapping("/user/borrow")
    public ResponseEntity<String> borrowBook(
            @RequestBody InitiateTransactionRequest initiateTransactionRequest) {

        initiateTransactionRequest.setTransactionType(TransactionType.ISSUE);
        try {
            transactionService.initiateTxn(initiateTransactionRequest);
            return ResponseEntity.ok("Book borrowed successfully.");
        } catch (HandleTransactionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (HandleBookException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


/*        String bookId = borrowBookRequest.getBookId();
        String userId = borrowBookRequest.getUserId();

        Users user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);

        if (user == null || book == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            bookService.borrowBook(user, book);
            return ResponseEntity.ok("Book borrowed successfully.");
        } catch (HandleBookException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }*/

    }


    @PostMapping("/user/return")
    public ResponseEntity<String> returnBook(
            @RequestBody InitiateTransactionRequest initiateTransactionRequest) throws HandleBookException {


        initiateTransactionRequest.setTransactionType(TransactionType.RETURN);
        try {
            transactionService.initiateTxn(initiateTransactionRequest);
            return ResponseEntity.ok("Book returned successfully.");
        } catch (HandleTransactionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (HandleBookException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/vailable-books")
    public List<Book> getAvailableBooks()
    {
        return bookService.getAvailable();
    }

}
