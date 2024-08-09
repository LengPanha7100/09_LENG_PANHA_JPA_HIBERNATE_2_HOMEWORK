package com.example.demospring.jpahibernate_homework.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title ;
    private String description;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private LocalDate publicationYear;
}
