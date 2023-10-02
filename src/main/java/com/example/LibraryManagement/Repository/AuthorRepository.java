package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByEmail(String email);
}
