package com.example.todoserver.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    @Override
    void deleteById(Integer todoId);

    @Override
    List<TodoEntity> findAll();
}
