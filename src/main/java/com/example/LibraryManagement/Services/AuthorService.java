package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getOrCreate(Author authors) {
        Author retrievedAuthor = authorRepository.findByEmail(authors.getEmail());
        if (retrievedAuthor == null) {
            retrievedAuthor = authorRepository.save(authors);
        }
        return retrievedAuthor;
    }
}
