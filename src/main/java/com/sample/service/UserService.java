package com.sample.service;

import com.sample.domain.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getList();

    User getUser(Long userId);

    User getUserByEmail(String email);

    void delete(Long userId);
}
