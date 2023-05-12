package com.example.todoserver.todo;

import com.example.todoserver.todo.dto.ResponseTodoDTO;
import com.example.todoserver.todo.dto.createTodoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

     @PostMapping("/todos")
     public ResponseEntity<ResponseTodoDTO> create(@RequestBody createTodoDTO todoEntity) {
        System.out.println("Controller " + todoEntity.getUserId());
        return ResponseEntity.created(null).body(todoService.create(todoEntity));
     }

     @GetMapping("/todos/{userId}")
     public ResponseEntity<List<ResponseTodoDTO>> findAllTodoEntitiesByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(todoService.findAllTodoEntitiesByUserId(userId));
    }
}
