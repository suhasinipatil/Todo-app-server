package com.example.todoserver.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    String username;
    String password;
    String email;
}
