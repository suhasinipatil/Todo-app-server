package com.example.todoserver.user;

import com.example.todoserver.Common.BaseEntity;
import com.example.todoserver.todo.TodoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false, length = 50)
    String username;
    String password;
    String email;

    @OneToMany
    List<TodoEntity> todoEntities;
}
