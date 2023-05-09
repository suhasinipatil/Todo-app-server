package com.example.todoserver.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createTodoDTO {
    String title;
    boolean completed;
    Integer userId;
}
