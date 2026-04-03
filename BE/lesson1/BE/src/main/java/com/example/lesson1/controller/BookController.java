package com.example.lesson1.controller;

import com.example.lesson1.DTO.BookDTO;
import com.example.lesson1.entity.Book;
import com.example.lesson1.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBook();
    }

    @PostMapping
    public Book createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")String id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id")String id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id,bookDTO);
    }
}
