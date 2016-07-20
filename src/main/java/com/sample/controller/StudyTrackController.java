package com.sample.controller;

import com.sample.domain.Book;
import com.sample.domain.Course;
import com.sample.domain.User;
import com.sample.service.BookService;
import com.sample.service.CourseService;
import com.sample.service.UserService;
import com.sample.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class StudyTrackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyTrackController.class);
    private final UserService userService;
    private final BookService bookService;
    private final CourseService courseService;

    @Inject
    public StudyTrackController(final UserService userService, BookService bookService, CourseService courseService) {
        this.userService = userService;
        this.bookService = bookService;
        this.courseService = courseService;
    }
    /*----------------User services -------------------*/
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userService.getAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to retrieve user : {}", id);
        return userService.get(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.create(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User updateUser(@RequestBody final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.update(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") final Long userId) {
        LOGGER.debug("Received request to delete the {}", userId);
        userService.delete(userId);
    }

    /*----------------Book services -------------------*/
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> listBooks() {
        LOGGER.debug("Received request to list all books");
        return bookService.getAll();
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to retrieve book : {}", id);
        return bookService.get(id);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book createBook(@RequestBody final Book book) {
        LOGGER.debug("Received request to create the {}", book);
        return bookService.create(book);
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public Book updateUser(@RequestBody final Book book) {
        LOGGER.debug("Received request to create the {}", book);
        return bookService.update(book);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") final Long userId) {
        LOGGER.debug("Received request to delete the {}", userId);
        bookService.delete(userId);
    }

    /*----------------Course services -------------------*/
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public List<Course> listCourses() {
        LOGGER.debug("Received request to list all courses");
        return courseService.getAll();
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to retrieve course : {}", id);
        return courseService.get(id);
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public Course createBook(@RequestBody final Course course) {
        LOGGER.debug("Received request to create the {}", course);
        return courseService.create(course);
    }

    @RequestMapping(value = "/course", method = RequestMethod.PUT)
    public Course updateUser(@RequestBody final Course course) {
        LOGGER.debug("Received request to create the {}", course);
        return courseService.update(course);
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to delete the {}", id);
        courseService.delete(id);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserServiceException(ServiceException e) {
        return e.getMessage();
    }

}
