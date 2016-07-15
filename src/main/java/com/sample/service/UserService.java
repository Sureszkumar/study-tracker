package com.sample.service;

import com.sample.domain.UserProfile;

import java.util.List;

public interface UserService {

    UserProfile save(UserProfile userProfile);

    List<UserProfile> getList();

    UserProfile getUser(Long userId);

    UserProfile getUserByEmail(String email);

    void delete(Long userId);
}
