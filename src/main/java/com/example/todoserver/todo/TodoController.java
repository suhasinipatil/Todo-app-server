package com.example.todoserver.todo;

import com.example.todoserver.todo.dto.ResponseTodoDTO;
import com.example.todoserver.todo.dto.createTodoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

     @PostMapping("")
     public ResponseEntity<ResponseTodoDTO> create(@RequestBody createTodoDTO todoEntity) {
        return ResponseEntity.ok(todoService.create(todoEntity));
     }
}
