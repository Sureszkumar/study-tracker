package com.sample.service;

import com.sample.domain.UserCourse;
import com.sample.repository.UserCourseRepository;
import com.sample.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class UserCourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCourseService.class);
    private final UserCourseRepository repository;

    @Inject
    public UserCourseService(final UserCourseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserCourse create(@NotNull final UserCourse course) {
        LOGGER.debug("Creating {}", course);
        course.setCreationDateTime(LocalDateTime.now());
        course.setLastChangeTimestamp(LocalDateTime.now());
        return repository.save(course);
    }

    @Transactional
    public UserCourse update(@NotNull final UserCourse course) {
        LOGGER.debug("Updating {}", course);
        course.setLastChangeTimestamp(LocalDateTime.now());
        return repository.save(course);
    }

    @Transactional
    public void delete(Long courseId) {
        LOGGER.debug("Deleting {}", courseId);
        UserCourse existing = repository.findOne(courseId);
        if (existing == null) {
            throw new ServiceException(
                    String.format("course with id =%s not exist", courseId));
        }
        repository.delete(courseId);
    }

    @Transactional(readOnly = true)
    public UserCourse get(Long courseId) {
        LOGGER.debug("Retrieving the course : {}", courseId);
        UserCourse course = repository.findOne(courseId);
        if (course == null) {
            throw new ServiceException(
                    String.format("UserCourse with id=%s is not exist", courseId));
        }
        return course;
    }

    @Transactional(readOnly = true)
    public List<UserCourse> getAll() {
        LOGGER.debug("Retrieving the list of all courses");
        return repository.findAll();
    }


}
