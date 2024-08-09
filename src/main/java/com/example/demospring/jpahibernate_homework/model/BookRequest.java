package com.example.demospring.jpahibernate_homework.model;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title ;
    private String description;
    private String author;
    private LocalDate publicationYear;
}
