package com.sample.service;

import com.sample.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getList();

}
