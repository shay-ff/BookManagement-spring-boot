package com.project.bookstore.controller;

import com.project.bookstore.model.Book;
import com.project.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/{id}/rent")
    public boolean rentBook(@PathVariable Long id) {
        return bookService.rentBook(id);
    }

    @PostMapping("/{id}/return")
    public boolean returnBook(@PathVariable Long id) {
        return bookService.returnBook(id);
    }
}
