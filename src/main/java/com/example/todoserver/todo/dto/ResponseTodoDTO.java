package com.example.todoserver.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTodoDTO {
    Integer id;
    String title;
    boolean completed;
}
