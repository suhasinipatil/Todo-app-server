package com.example.todoserver.user;

import com.example.todoserver.user.dto.CreateUserDTO;
import com.example.todoserver.user.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) throws URISyntaxException {
        var createdUser = userService.createUser(createUserDTO);
        return ResponseEntity.created(new URI("/login/" + createdUser.getId())).body(createdUser);
    }

    //get all users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() throws URISyntaxException {
        var createdUser = userService.getAllUsers();
        List<UserResponseDTO> lsOfUsers = new ArrayList<>();
        for (UserEntity userEntity : createdUser) {
            var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);
            lsOfUsers.add(userResponseDTO);
        }
        return ResponseEntity.ok().body(lsOfUsers);
    }
}
