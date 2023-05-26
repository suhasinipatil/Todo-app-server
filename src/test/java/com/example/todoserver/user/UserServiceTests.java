package com.example.todoserver.user;

import com.example.todoserver.user.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class UserServiceTests {
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

    @Test
    public void createUser(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("abc");
        createUserDTO.setPassword("abc");
        createUserDTO.setEmail("abc@gmail.com");
        var savedUser = getUserService().createUser(createUserDTO);
        assertNotNull(savedUser);
    }

    @Test
    public void getAllUsers(){

        var savedUser = getUserService().getAllUsers();
        assertNotNull(savedUser);
    }

    @Test
    public void findByUsername() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("abc");
        createUserDTO.setPassword("abc");
        createUserDTO.setEmail("abc@gmail.com");
        var savedUser = getUserService().createUser(createUserDTO);
    }
}
