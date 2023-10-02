package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTO.CreateAdminRequest;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Models.Users;
import com.example.LibraryManagement.Services.AdminService;
import com.example.LibraryManagement.Services.BookService;
import com.example.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;

    @GetMapping("/user/{id}")
    public Users findById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable String id)
    {
        return bookService.findBookById(id);
    }

    @PostMapping("/create/admin")
    public void createAdmin(@RequestBody CreateAdminRequest createAdminRequest) {
        adminService.createOrUpdateAdmin(createAdminRequest.to());
    }

}
