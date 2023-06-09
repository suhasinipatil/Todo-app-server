package com.example.todoserver.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

    Optional<UserEntity> findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_todo_entities (users_id, todo_entities_id) VALUES (:userId, :todoId)", nativeQuery = true)
    void addTodoEntityByUserId(Integer userId, Integer todoId);


}
