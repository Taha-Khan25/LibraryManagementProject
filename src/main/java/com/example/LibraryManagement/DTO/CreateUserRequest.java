package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Models.SecuredUser;
import com.example.LibraryManagement.Models.Users;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    private String email;
    private String name;

    private String username;
    private String password;


    public Users to()
    {
        return  Users.builder()
                .email(this.email)
                .name(this.name)
                .borrowedBooks(new ArrayList<>())
                .securedUser(
                        SecuredUser.builder()
                                .username(this.username)
                                .password(this.password)
                                .build())
                .build();
    }
}
