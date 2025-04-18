package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean rentBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent() && optionalBook.get().getAvailable()) {
            Book book = optionalBook.get();
            book.setAvailable(false);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent() && !optionalBook.get().getAvailable()) {
            Book book = optionalBook.get();
            book.setAvailable(true);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public int getPrice(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(book -> (int) book.getPrice()).orElse(0);
    }
}
