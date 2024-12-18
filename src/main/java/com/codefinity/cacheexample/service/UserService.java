package com.codefinity.cacheexample.service;

import com.codefinity.cacheexample.dto.UserRequestDTO;
import com.codefinity.cacheexample.dto.UserResponseDTO;
import com.codefinity.cacheexample.model.User;

public interface UserService {
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO user);
}
