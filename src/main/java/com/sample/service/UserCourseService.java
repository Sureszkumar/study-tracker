package com.sample.service;

import com.sample.domain.UserCourse;
import com.sample.repository.UserCourseRepository;
import com.sample.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
