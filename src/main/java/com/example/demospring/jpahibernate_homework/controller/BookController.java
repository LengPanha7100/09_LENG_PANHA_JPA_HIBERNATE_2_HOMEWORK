package com.example.demospring.jpahibernate_homework.controller;

import com.example.demospring.jpahibernate_homework.model.APIResponseBook;
import com.example.demospring.jpahibernate_homework.model.Book;
import com.example.demospring.jpahibernate_homework.model.BookRequest;
import com.example.demospring.jpahibernate_homework.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBodyReturnValueHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllBook () {
        List<Book> book = bookRepository.getAllBook();
        APIResponseBook<?> apiResponseBook = new APIResponseBook<>(
                "The book get all successfully!",
                book,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponseBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable UUID id){
         Book book = bookRepository.getById(id);
         APIResponseBook<?> apiResponseBook = new APIResponseBook<>(
                 "The book get by is successfully!",
                 book,
                 HttpStatus.OK,
                 LocalDateTime.now()
         );
        return ResponseEntity.ok(apiResponseBook);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getByTitle(@RequestParam String title){
        List<Book> books = bookRepository.getByTitle(title);
        APIResponseBook<?> apiResponseBook = new APIResponseBook<>(
                "The book get by title successfully!",
                books,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponseBook);

    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest bookRequest){
        Book book1 = bookRepository.createBook(bookRequest);
        APIResponseBook<?> apiResponseBook = new APIResponseBook<>(
                "The book created successfully!",
                book1,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookRequest bookRequest, @PathVariable UUID id){
        Book book1 = bookRepository.updateBook(bookRequest,id);
        APIResponseBook<?> apiResponseBook = new APIResponseBook<>(
                "The book is updated successfully!",
                book1,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponseBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id){
        Book book = bookRepository.deleteBook(id);
        APIResponseBook<?> apiResponseBook = new APIResponseBook<>(
                "The book is deleted successfully!",
                book,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponseBook);
    }


}
