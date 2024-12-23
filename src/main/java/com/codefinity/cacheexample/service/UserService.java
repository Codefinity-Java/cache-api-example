package com.codefinity.cacheexample.service;

import com.codefinity.cacheexample.model.User;

public interface UserService {
    User getUserById(Long id);
    User createUser(User user);
    void deleteUser(Long id);
}
