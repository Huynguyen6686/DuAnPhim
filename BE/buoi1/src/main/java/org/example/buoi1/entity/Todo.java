package org.example.buoi1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;
    @Size(min = 3, max = 50,message = "title must be between 3 and 50 characters")
//    @Size(min = 3, max = 50,message = "title nhập từ 3 đến 50 kí tự")
    private String title;
    @Size(min = 3, message = "description must be at least 3 characters")
//    @Size(min = 3, message = "description nhập ít nhất 3 kí tự")
    private String description;

    private boolean completed;


}
