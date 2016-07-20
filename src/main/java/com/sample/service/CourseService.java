package com.sample.service;

import com.sample.domain.Course;
import com.sample.repository.CourseRepository;
import com.sample.service.exception.ServiceException;
import com.sample.util.ServiceUtils;
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
public class CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository repository;

    @Inject
    public CourseService(final CourseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Course create(@NotNull final Course course) {
        LOGGER.debug("Creating {}", course);
        course.setCreationDateTime(LocalDateTime.now());
        course.setLastChangeTimestamp(LocalDateTime.now());
        Course newCourse = repository.save(course);
        return repository.save(newCourse);
    }

    @Transactional
    public Course update(@NotNull final Course course) {
        LOGGER.debug("Updating {}", course);
        course.setLastChangeTimestamp(LocalDateTime.now());
        return repository.save(course);
    }

    @Transactional
    public void delete(Long courseId) {
        LOGGER.debug("Deleting {}", courseId);
        Course existing = repository.findOne(courseId);
        if (existing == null) {
            throw new ServiceException(
                    String.format("course with id =%s not exist", courseId));
        }
        repository.delete(courseId);
    }

    @Transactional(readOnly = true)
    public Course get(Long courseId) {
        LOGGER.debug("Retrieving the course : {}", courseId);
        Course course = repository.findOne(courseId);
        if (course == null) {
            throw new ServiceException(
                    String.format("Course with id=%s is not exist", courseId));
        }
        return course;
    }

    @Transactional(readOnly = true)
    public List<Course> getAll() {
        LOGGER.debug("Retrieving the list of all courses");
        return repository.findAll();
    }


}
