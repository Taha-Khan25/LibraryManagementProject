package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Models.SecuredUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecuredUserRepository extends MongoRepository<SecuredUser,String> {

    SecuredUser findByUsername(String username);
}
