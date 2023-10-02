package com.example.LibraryManagement.Controllers;


import com.example.LibraryManagement.DTO.CreateBookRequest;
import com.example.LibraryManagement.DTO.UpdateBookRequest;
import com.example.LibraryManagement.Exceptions.HandleBookException;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Services.BookService;
import com.example.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @PostMapping("/create-book")
    public void createBook(@RequestBody CreateBookRequest createBookRequest)
    {
        bookService.createOrUpdate(createBookRequest.to());
    }

    @PutMapping("/update-book")
    public Book updateBook(@RequestBody UpdateBookRequest updateBookRequest) throws HandleBookException {
         return bookService.update(updateBookRequest.to());
    }

    @GetMapping("/book/list")
    public List<Book> getAllBook()
    {
        return bookService.getAlBook();
    }

    @DeleteMapping("/book/delete/{id}")
    public Book deleteBook(@PathVariable String id)
    {
        return bookService.deleteBook(id);
    }



}
