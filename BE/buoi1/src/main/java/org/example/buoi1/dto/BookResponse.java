package org.example.buoi1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponse {
    //hiển thị dữ liệu lên bảng
    private String title;
    private String author;
    private double price;
    private String isbn;

}
