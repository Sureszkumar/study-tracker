package com.sample.service;

import com.sample.domain.User;
import com.sample.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

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
