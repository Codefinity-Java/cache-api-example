package com.codefinity.cacheexample.service.impl;

import com.codefinity.cacheexample.dto.UserRequestDTO;
import com.codefinity.cacheexample.dto.UserResponseDTO;
import com.codefinity.cacheexample.exception.NotFoundException;
import com.codefinity.cacheexample.mapper.MapperUser;
import com.codefinity.cacheexample.model.User;
import com.codefinity.cacheexample.repository.UserRepository;
import com.codefinity.cacheexample.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.user.hash}")
    private String redisHash;

    public UserServiceImpl(UserRepository userRepository, RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public UserResponseDTO getUserById(Long id) {
        String hashKey = id.toString();

        Object cachedUser = redisTemplate.opsForHash().get(redisHash, hashKey);
        if (cachedUser != null) {
            return MapperUser.modelToResponseDto((User) cachedUser);
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with this id: %d not found", id))
        );

        redisTemplate.opsForHash().put(redisHash, hashKey, user);

        return MapperUser.modelToResponseDto(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO user) {
        User mapperToUser = MapperUser.dtoRequestToModel(user);
        User savedUser = userRepository.save(mapperToUser);

        redisTemplate.opsForHash().put(redisHash, savedUser.getId().toString(), savedUser);

        return MapperUser.modelToResponseDto(savedUser);
    }
}

