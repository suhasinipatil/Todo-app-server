package com.example.todoserver.todo;

import com.example.todoserver.Common.BaseEntity;
import com.example.todoserver.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity(name = "todos")
public class TodoEntity extends BaseEntity {
    @Column(nullable = false, length = 200)
    String title;

    //@ManyToOne
   // UserEntity user;

    boolean completed = false;

}
