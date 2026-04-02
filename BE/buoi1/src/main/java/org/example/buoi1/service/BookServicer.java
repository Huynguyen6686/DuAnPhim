package org.example.buoi1.service;

import org.example.buoi1.dto.BookRequest;
import org.example.buoi1.dto.BookResponse;
import org.example.buoi1.entity.Book;

import java.util.List;

public interface BookServicer {

    List<BookResponse> findAll();

    BookResponse findById(long id);

    BookResponse add(BookRequest bookRequest);

    BookResponse update(BookRequest bookRequest, long id);

    // BookResponse delete(long id);
    //có thể dùng hàm void được
    void delete(long id);
}
