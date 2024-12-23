package com.codefinity.cacheexample.service.impl;

import com.codefinity.cacheexample.model.User;
import com.codefinity.cacheexample.repository.UserRepository;
import com.codefinity.cacheexample.service.UserService;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user) {
        return  userRepository.save(user);
    }

    @Transactional
    @Cacheable(value = "user-cache", key = "#id")
    @SneakyThrows
    public User getUserById(Long id) {
        Thread.sleep(100);
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("User with this id: %d not found", id))
        );
    }

    @CacheEvict(value="user-cache", key="#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

