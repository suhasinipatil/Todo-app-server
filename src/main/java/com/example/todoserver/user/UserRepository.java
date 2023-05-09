package com.example.todoserver.user;

import com.example.todoserver.todo.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

    List<TodoEntity> findAllTodoEntitiesByUsername(String username);

    //insert in users_todo_entities
    @Query(value = "insert into users_todo_entities (user_id, todo_id) values (?1, ?2)", nativeQuery = true)
    void addTodoEntityByUserId(Integer userId, Integer todoId);
}
