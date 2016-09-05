package com.sample.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sample.domain.User;
import com.sample.repository.UserRepository;

@Service
@Validated
public class UserService extends BaseService<User>{

    @Inject
    private UserRepository userRepository;

    @PostConstruct
    protected void init() {
        super.init(userRepository);
    }

}
