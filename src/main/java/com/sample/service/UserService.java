package com.sample.service;

import com.sample.domain.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    List<User> getList();

    User getUser(Long userId);

    void delete(Long userId);
}
