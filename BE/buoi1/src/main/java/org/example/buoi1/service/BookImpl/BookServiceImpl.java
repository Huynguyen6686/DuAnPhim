package org.example.buoi1.service.BookImpl;

import lombok.RequiredArgsConstructor;
import org.example.buoi1.dto.BookRequest;
import org.example.buoi1.dto.BookResponse;
import org.example.buoi1.entity.Book;
import org.example.buoi1.exception.CustomResourceNotFoundException;
import org.example.buoi1.repository.BookRepository;
import org.example.buoi1.service.BookServicer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServicer {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().
                map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }
    private String generateIsbn() {
        return "ISBN: " + UUID.randomUUID().toString().substring(0,13);
    }
    @Override
    public BookResponse findById(long id) {
        return bookRepository.findById(id)
                .map(book -> modelMapper.map(book, BookResponse.class))
                .orElseThrow(() -> new CustomResourceNotFoundException("Book not found for this id: " + id));
    }

    @Override
    public BookResponse add(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        book.setIsbn(generateIsbn());

        bookRepository.save(book);

        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return bookResponse;
    }

    @Override
    public BookResponse update(BookRequest bookRequest, long id) {
        return bookRepository.findById(id)
                .map(b->{
                    if (bookRequest.getTitle() != null) {
                        b.setTitle(bookRequest.getTitle());
                    }
                    else if (bookRequest.getAuthor() != null) {
                        b.setAuthor(bookRequest.getAuthor());
                    }
                    else if (bookRequest.getPrice() >0) {
                        b.setPrice(bookRequest.getPrice());
                    }
                    bookRepository.save(b);
                    return modelMapper.map(b, BookResponse.class);
                })
                .orElseThrow(() -> new CustomResourceNotFoundException("Book not found for this id: " + id));
    }

    @Override
    public void delete(long id) {
        BookResponse bookResponse = findById(id);

        if(bookResponse != null) {
            Book book = modelMapper.map(bookResponse, Book.class);

            bookRepository.deleteById(book.getId());
        }
    }

    
}
