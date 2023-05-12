package com.example.todoserver.todo;

import com.example.todoserver.todo.dto.ResponseTodoDTO;
import com.example.todoserver.todo.dto.createTodoDTO;
import com.example.todoserver.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    private final UserService  userService;
    private final ModelMapper modelMapper;

    public TodoService(TodoRepository todoRepository, UserService userService, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

     public ResponseTodoDTO create(createTodoDTO todoEntity) {
        var todo = modelMapper.map(todoEntity, TodoEntity.class);
        var savedTodo =  todoRepository.save(todo);
        System.out.println("todoEntity.userId " + todoEntity.getUserId() + " savedTodo.id " + savedTodo.getId());
        userService.addTodoEntityByUserId(todoEntity.getUserId(), savedTodo.getId());
        var responseTodo = modelMapper.map(savedTodo, ResponseTodoDTO.class);
        return responseTodo;
     }

    public List<ResponseTodoDTO> findAllTodoEntitiesByUserId(Integer userId) {
        var todoEntities = todoRepository.findTodosByUserId(userId);
        List<ResponseTodoDTO> responseTodoDTOList = new ArrayList<>();
        for (TodoEntity todoEntity : todoEntities) {
            var responseTodo = modelMapper.map(todoEntity, ResponseTodoDTO.class);
            responseTodoDTOList.add(responseTodo);
        }
        return responseTodoDTOList;
    }
}
