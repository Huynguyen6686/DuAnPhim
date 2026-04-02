package org.example.buoi1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
//dùng vali
    private String title;
    private String author;
    private double price;
   //có thể bỏ đc cái isbn ->  private String isbn;


}
