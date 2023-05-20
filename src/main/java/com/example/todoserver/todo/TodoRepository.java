package com.example.todoserver.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {


    @Query(value = "SELECT t.* FROM todos t INNER JOIN users_todo_entities ute ON t.id = ute.todo_entities_id WHERE ute.users_id = :userId", nativeQuery = true)
    List<TodoEntity> findTodosByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users_todo_entities WHERE todo_entities_id = :todoId", nativeQuery = true)
    void deleteTodoEntityFromUsersTodoEntities(Integer todoId);

    @Override
    void deleteById(Integer todoId);

    @Override
    Optional<TodoEntity> findById(Integer integer);

    @Override
    List<TodoEntity> findAll();
}
