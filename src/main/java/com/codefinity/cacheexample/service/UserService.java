package com.codefinity.cacheexample.service;

import com.codefinity.cacheexample.model.User;
import com.codefinity.cacheexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.user.hash}")
    private String redisHash;

    public UserService(UserRepository userRepository, RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }
    @Transactional
    public User getUserById(Long id) {
        String hashKey = id.toString();

        Object cachedUser = redisTemplate.opsForHash().get(redisHash, hashKey);
        if (cachedUser != null) {
            return (User) cachedUser;
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        redisTemplate.opsForHash().put(redisHash, hashKey, user);
        return user;
    }
    @Transactional
    public User createUser(User user) {
        User savedUser = userRepository.save(user);

        redisTemplate.opsForHash().put(redisHash, savedUser.getId().toString(), savedUser);
        return savedUser;
    }
}

