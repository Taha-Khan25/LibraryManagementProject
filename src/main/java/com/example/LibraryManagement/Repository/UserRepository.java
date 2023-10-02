package com.example.LibraryManagement.Repository;
import com.example.LibraryManagement.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users,String> {

}
