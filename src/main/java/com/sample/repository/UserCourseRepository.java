package com.sample.repository;

import com.sample.domain.Course;
import com.sample.domain.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

}
