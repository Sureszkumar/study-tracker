package com.sample.service;

import com.sample.domain.BaseDomain;
import com.sample.domain.Course;
import com.sample.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service
@Validated
public class CourseService extends BaseService<Course>{

    @Inject
    private CourseRepository courseRepository;

    @PostConstruct
    protected void init() {
        super.init(courseRepository);
    }
}
