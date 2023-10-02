package com.example.LibraryManagement.Services;


import com.example.LibraryManagement.Exceptions.HandleBookException;
import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Models.Users;
import com.example.LibraryManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthorService authorService;

    public void createOrUpdate(Book book)
    {
        Author bookAuthor = book.getAuthor();
        Author savedAuthor = authorService.getOrCreate(bookAuthor);
        book.setAuthor(savedAuthor);
        bookRepository.save(book);
    }

    public Book update(Book book) throws HandleBookException {
        String bookId=book.getId();

        Book savedBook=bookRepository.findById(bookId).orElse(null);
        if (savedBook!=null)
        {
            savedBook.setIsbn(book.getIsbn());
            savedBook.setQuantity(book.getQuantity());
            savedBook.setTitle(book.getTitle());
            savedBook.setPublishedYear(book.getPublishedYear());


            Author bookAuthor = book.getAuthor();
            Author savedAuthor = authorService.getOrCreate(bookAuthor);
            savedBook.setAuthor(savedAuthor);
            return bookRepository.save(savedBook);
        }
        else {
            throw new HandleBookException("Book Not Found.");
        }

    }

    public Book findBookById(String id) {
     return  bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAlBook()
    {
        return bookRepository.findAll();
    }

    public Book deleteBook(String id) {
        Book book=bookRepository.findById(id).orElse(null);
        if(book!=null) {
            bookRepository.deleteById(id);
            return book;
        }
        return null;
    }

    public void borrowBook(Users user, Book book) throws HandleBookException {
        if(user.getBorrowedBooks().size()==3)
        {
            throw new HandleBookException("Max book availed.");
        }
        if (user.getBorrowedBooks().contains(book)) {
            throw new HandleBookException("Book already availed");
        }
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
            List<Book> boorowed=user.getBorrowedBooks();
            boorowed.add(book);
            user.setBorrowedBooks(boorowed);
            userService.createOrUpdateUser(user);
        } else {
            throw new HandleBookException("This book is not available.");
        }
    }

    public void returnBook(Users user, Book book) throws HandleBookException {

        List<Book> borrowedBooks = user.getBorrowedBooks();
        String bookId=book.getId();
        boolean found = false;
        for (Book books : borrowedBooks) {
            if (books.getId().equals(bookId)) {
                borrowedBooks.remove(books);
                found = true;
                break;
            }
        }
        if (found) {
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
            user.setBorrowedBooks(borrowedBooks);
            userService.createOrUpdateUser(user);
        } else {
            throw new HandleBookException("The user has not borrowed this book.");
        }
    }

    private boolean userHasBorrowedBook(Users user, String bookId) {
        return user.getBorrowedBooks().stream().anyMatch(book -> book.getId().equals(bookId));
    }


    public List<Book> getAvailable() {
        return bookRepository.findByQuantityGreaterThan(0);
    }
}

