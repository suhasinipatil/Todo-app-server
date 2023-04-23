package com.example.todoserver.user;

import com.example.todoserver.user.dto.CreateUserDTO;
import com.example.todoserver.user.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        var userEntity = userRepository.findByUsername(createUserDTO.getUsername());
        if(userEntity != null){
            throw new UserAlreadyExistsException(createUserDTO.getUsername());
        }
        var newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        var savedUser = userRepository.save(newUserEntity);
        var userResponseDto = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponseDto;
    }

    public static class UserAlreadyExistsException extends IllegalArgumentException {
        public UserAlreadyExistsException(String username){
            super(username + " already exists");
        }
    }
}