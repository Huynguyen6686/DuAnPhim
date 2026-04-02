package org.example.buoi1.contronller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.buoi1.entity.Todo;
import org.example.buoi1.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoContronller {
    private final TodoService todoService;
    @GetMapping()
    public ResponseEntity<List<Todo>>  getAll(){
        List<Todo> todos=todoService.findAll();
        //Cách 1
       return new ResponseEntity<>(todos, HttpStatus.OK);
        //Cách 2
       // return ResponseEntity.ok(todos);
        //Cách 3
       // return ResponseEntity.status(HttpStatus.OK).body(todos);
        //Cách 4
//        return ResponseEntity
//                .ok()
//                .header("Custom-Header","Custom-Value")
//                .body(todos);
    }
    @GetMapping("{id}")
    public ResponseEntity<Todo>  getTodosById(@PathVariable("id") long id) {
        Todo todo=todoService.findById(id);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo > addTodos(@Valid @RequestBody Todo todo){
        Todo todo1 =todoService.add(todo);
        return new ResponseEntity<>(todo1,HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Todo>  updateTodo(@Valid @RequestBody Todo todo, @PathVariable long id){
        Todo todo1=todoService.update(todo,id);
        return new ResponseEntity<>(todo1,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Todo>  deleteTodo(@PathVariable long id){
        Todo todo=todoService.delete(id);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }


}
