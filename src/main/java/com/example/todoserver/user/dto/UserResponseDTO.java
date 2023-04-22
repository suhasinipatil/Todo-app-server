package com.example.todoserver.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    Integer id;
    String username;
    String email;
}
