package org.example.buoi1.contronller;

import lombok.RequiredArgsConstructor;
import org.example.buoi1.dto.BookRequest;
import org.example.buoi1.dto.BookResponse;
import org.example.buoi1.service.BookServicer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServicer bookServicer;
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookServicer.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookServicer.findById(id));
    }
    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookServicer.add(bookRequest));
    }
    @PutMapping("{id}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest,@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookServicer.update(bookRequest, id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookServicer.delete(id);
        return ResponseEntity.noContent().build();//204
    }
}
