package com.sample.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sample.domain.UserCourse;
import com.sample.repository.UserCourseRepository;

@Service
@Validated
public class UserCourseService extends BaseService<UserCourse>{

    @Inject
    private UserCourseRepository repository;

    @PostConstruct
    protected void init() {
        super.init(repository);
    }

}
