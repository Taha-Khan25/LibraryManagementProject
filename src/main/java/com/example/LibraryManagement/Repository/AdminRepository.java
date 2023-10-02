package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {
}
