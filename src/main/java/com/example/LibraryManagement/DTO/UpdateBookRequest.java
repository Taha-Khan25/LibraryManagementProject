package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Models.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookRequest {

    private String id;
    private Integer isbn;

    private String publishedYear;

    private String title;

    private Integer quantity;

    private String authorName;

    private String authorEmail;


    public Book to()
    {
        return Book.builder()
                .id(this.id)
                .isbn(this.isbn)
                .title(this.title)
                .publishedYear(this.publishedYear)
                .quantity(this.quantity)
                .author(Author.builder()
                        .name(this.authorName)
                        .email(this.authorEmail)
                        .build())
                .build();
    }

}