package com.project.bookstore.repository;

import java.util.List;
import com.project.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // This interface will automatically provide CRUD operations for the Book entity
    // No need to implement any methods here, JpaRepository provides them out of the box

    List<Book> findByTitle(String title);
    // Find all books by genre
    List<Book> findByGenre(String genre);

    // Find all available books
    List<Book> findByAvailableTrue();

    // Optional: fuzzy search (e.g., all authors whose name contains "Rowling")
    List<Book> findByAuthorContaining(String keyword);

}
