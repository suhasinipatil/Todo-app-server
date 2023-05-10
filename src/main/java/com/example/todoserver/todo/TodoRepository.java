package com.example.todoserver.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    @Override
    void deleteById(Integer todoId);

    @Query(value = "SELECT t.* FROM todos t INNER JOIN users_todo_entities ute ON t.id = ute.todo_entities_id WHERE ute.users_id = :userId", nativeQuery = true)
    List<TodoEntity> findTodosByUserId(Integer userId);

    @Override
    List<TodoEntity> findAll();
}
