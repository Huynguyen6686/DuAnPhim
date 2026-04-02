package org.example.buoi1.service;

import org.example.buoi1.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo findById(long id);

    Todo add(Todo todo);

    Todo update(Todo todo,long id);

    Todo delete(long id);


}
