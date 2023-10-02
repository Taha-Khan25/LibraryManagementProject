package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Models.Admin;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAdminRequest {

    private String name;

    private String email;

    public Admin to()
    {
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
