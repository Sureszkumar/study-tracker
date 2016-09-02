package com.sample.controller;

import com.sample.domain.Book;
import com.sample.domain.Course;
import com.sample.domain.Image;
import com.sample.domain.User;
import com.sample.domain.UserCourse;
import com.sample.service.BookService;
import com.sample.service.CourseService;
import com.sample.service.EmailService;
import com.sample.service.ImageService;
import com.sample.service.UserCourseService;
import com.sample.service.UserService;
import com.sample.service.exception.ServiceException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.List;

@RestController
public class StudyTrackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyTrackController.class);
    @Inject
    private UserService userService;
    @Inject
    private BookService bookService;
    @Inject
    private CourseService courseService;
    @Inject
    private UserCourseService userCourseService;
    @Inject
    private ImageService imageService;
    @Inject
    private EmailService emailService;

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

    /*----------------Image services -------------------*/
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public List<Image> listImages() {
        LOGGER.debug("Received request to list all images");
        return imageService.getAll();
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public Image getImage(@PathVariable("id") final Long id) {
        LOGGER.debug("Received request to retrieve image : {}", id);
        return imageService.get(id);
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Image createImage(@RequestParam MultipartFile image) {
        LOGGER.debug("Received request to create image ");
        return imageService.uploadImage(image);
    }

    @RequestMapping(value = "/userCourse", method = RequestMethod.POST)
    public UserCourse createUserCourse(@RequestBody final UserCourse userCourse) {
        LOGGER.debug("Received request to create the {}", userCourse);
        return userCourseService.create(userCourse);
    }

    @RequestMapping(value = "/email/{toAddress}", method = RequestMethod.POST)
    public void sendEmail(@PathVariable("toAddress") final String toAddress) {
        LOGGER.debug("Sending verification email to {}", toAddress);
        emailService.sendMail(toAddress);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserServiceException(ServiceException e) {
        return e.getMessage();
    }

}
