package com.example.todoserver.todo;

import com.example.todoserver.todo.dto.createTodoDTO;
import com.example.todoserver.user.UserRepository;
import com.example.todoserver.user.UserService;
import com.example.todoserver.user.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TodoServiceTests {

    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    private UserService getUserService(){
        if(userService == null){
            var modelMapper = new ModelMapper();
            userService = new UserService(userRepository, modelMapper);
        }
        return userService;
    }

    private TodoService getTodoService(){
        if(todoService == null){
            var modelMapper = new ModelMapper();
            todoService = new TodoService(todoRepository, getUserService(), modelMapper);
        }
        return todoService;
    }

    @Test
    public void createTodo(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("abc");
        createUserDTO.setPassword("abc");
        createUserDTO.setEmail("abc@gmail.com");
        var savedUser = getUserService().createUser(createUserDTO);
        assertNotNull(savedUser);

        createTodoDTO createTodoDTO = new createTodoDTO();
        createTodoDTO.setUserId(savedUser.getId());
        createTodoDTO.setTitle("abc");
        createTodoDTO.setCompleted(false);
        var savedTodo = getTodoService().create(createTodoDTO);
        assertNotNull(savedTodo);

        var userWithTodo = getTodoService().findAllTodoEntitiesByUserId(savedUser.getId());
        assertNotNull(userWithTodo);
    }

}
