package com.sample.controller;

import com.sample.domain.User;
import com.sample.service.UserService;
import com.sample.service.exception.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userService.getList();
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable("email") final String email) {
        LOGGER.debug("Received request to retrieve user : {}", email);
        return userService.getUserByEmail(email);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.createUser(user);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") final Long userId) {
        LOGGER.debug("Received request to delete the {}", userId);
        userService.delete(userId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserServiceException(UserServiceException e) {
        return e.getMessage();
    }

}
