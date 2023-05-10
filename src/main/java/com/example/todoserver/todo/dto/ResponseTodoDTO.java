package com.example.todoserver.todo.dto;

import com.example.todoserver.todo.TodoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseTodoDTO {
    Integer id;
    String title;
    boolean completed;
}
