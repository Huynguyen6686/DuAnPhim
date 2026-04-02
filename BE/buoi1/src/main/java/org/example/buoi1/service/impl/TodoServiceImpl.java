package org.example.buoi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.buoi1.entity.Todo;
import org.example.buoi1.exception.CustomResourceNotFoundException;
import org.example.buoi1.repository.TodoRepository;
import org.example.buoi1.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository  todoRepository;
//alt+enter

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(long id) {
        return todoRepository
                .findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("hhi..." + id));
    }

    @Override
    public Todo add(Todo todo) {
        return  todoRepository.save(todo);

    }

    @Override
    public Todo update(Todo todo, long id) {
        return todoRepository.findById(id)
                .map(existing->{
            if (todo.getTitle()!=null) {
                existing.setTitle(todo.getTitle());
            }
            if (todo.getDescription()!=null) {
                existing.setDescription(todo.getDescription());
            }
//ham radio
            existing.setCompleted(todo.isCompleted());
            return todoRepository.save(existing);
        }).orElse(null);

    }

    @Override
    public Todo delete(long id) {
        Todo deleteTodo=findById(id);
        if (deleteTodo!=null){
            todoRepository.deleteById(id);
        }
        return deleteTodo;
    }
}
