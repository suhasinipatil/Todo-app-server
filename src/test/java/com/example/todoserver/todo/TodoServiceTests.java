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
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class TodoServiceTests {

    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    private UserService userService;

    private UserService getUserService(){
        if(userService == null){
            //var modelMapper = new ModelMapper();
            userService = new UserService(userRepository, modelMapper);
        }
        return userService;
    }

    private TodoService getTodoService(){
        if(todoService == null){
           // var modelMapper = new ModelMapper();
            todoService = new TodoService(todoRepository, getUserService(), modelMapper);
        }
        return todoService;
    }

    private CreateUserDTO createUser(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("abc");
        createUserDTO.setPassword("abc");
        createUserDTO.setEmail("abc@gmail.com");
        return createUserDTO;
    }

    private createTodoDTO createTodo(){
        createTodoDTO todoDTO = new createTodoDTO();
        todoDTO.setTitle("abc");
        todoDTO.setCompleted(false);
        return todoDTO;
    }

    @Test
    public void create(){
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

    @Test
    public void updateTodo() {
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

        var updatedTodo = getTodoService().updateTodoEntity(savedTodo.getId(), createTodoDTO);
        assertNotNull(updatedTodo);
    }

    @Test
    public void deleteTodo() {
        CreateUserDTO createUserDTO = createUser();
        var savedUser = getUserService().createUser(createUserDTO);
        assertNotNull(savedUser);

        createTodoDTO createTodoDTO = createTodo();
        createTodoDTO.setUserId(savedUser.getId());
        var savedTodo = getTodoService().create(createTodoDTO);
        assertNotNull(savedTodo);

        getTodoService().deleteTodoEntity(savedTodo.getId());
        var deletedTodo = getTodoService().findTodoEntityById(savedTodo.getId());
        assertNull(deletedTodo);
    }
}
