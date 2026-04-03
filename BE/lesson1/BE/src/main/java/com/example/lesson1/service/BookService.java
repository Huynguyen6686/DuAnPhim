package com.example.lesson1.service;

import com.example.lesson1.DTO.BookDTO;
import com.example.lesson1.entity.Book;
import com.example.lesson1.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setCategory(bookDTO.getCategory());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        return bookRepository.save(book);
    }

    public void deleteBook(String id) {
         bookRepository.deleteById(id);
    }

    public Book updateBook(String id,BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setName(bookDTO.getName());
        book.setCategory(bookDTO.getCategory());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        return bookRepository.save(book);
    }
}
