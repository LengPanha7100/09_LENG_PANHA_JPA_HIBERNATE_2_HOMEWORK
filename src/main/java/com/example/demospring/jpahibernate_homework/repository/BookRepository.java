package com.example.demospring.jpahibernate_homework.repository;

import com.example.demospring.jpahibernate_homework.model.Book;
import com.example.demospring.jpahibernate_homework.model.BookRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Book createBook(BookRequest bookRequest) {

        //Transient state
       Book book1 = new Book(
               null,
               bookRequest.getTitle(),
               bookRequest.getDescription(),
               bookRequest.getAuthor(),
               bookRequest.getPublicationYear()
       );
        //Persist
       entityManager.persist(book1);
       return book1;
    }

    public List<Book> getAllBook() {
        List<Book> book1 = entityManager.createNativeQuery("SELECT * FROM book", Book.class).getResultList();
        return book1;
    }

    public Book getById(UUID id) {
        Book book1 = entityManager.find(Book.class,id);

        return book1;
    }

    public Book updateBook(BookRequest bookRequest, UUID id) {
        Book book1 = entityManager.find(Book.class,id);
        entityManager.detach(book1);
            book1.setTitle(bookRequest.getTitle());
            book1.setAuthor(bookRequest.getAuthor());
            book1.setDescription(bookRequest.getDescription());
            book1.setPublicationYear(bookRequest.getPublicationYear());
        entityManager.merge(book1);
        return book1;
    }



    public Book deleteBook(UUID id) {
        Book book1 = entityManager.find(Book.class,id);
        entityManager.remove(book1);
        return book1;
    }

    public List<Book> getByTitle(String title) {
        List<Book> books = entityManager.createNativeQuery("SELECT * FROM book WHERE title LIKE '%"+title+"%'",Book.class).getResultList();
        return books;
    }
}
