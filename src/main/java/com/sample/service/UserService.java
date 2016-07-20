package com.sample.service;

import com.sample.domain.User;
import com.sample.repository.UserRepository;
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
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    @Inject
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User create(@NotNull final User user) {
        LOGGER.debug("Creating {}", user);
        User existing = repository.getUserByMobile(user.getMobile());
        if (existing != null) {
            throw new ServiceException(
                    String.format("There already exists a user with mobile =%s", user.getMobile()));
        }
        user.setCreationDateTime(LocalDateTime.now());
        user.setLastChangeTimestamp(LocalDateTime.now());
        User newUser = repository.save(user);
        newUser.setAuthToken(ServiceUtils.createAuthToken(newUser.getId()));
        User savedUser = repository.save(newUser);
        return savedUser;
    }

    @Transactional
    public User update(@NotNull final User user) {
        LOGGER.debug("Updating {}", user);
        user.setLastChangeTimestamp(LocalDateTime.now());
        return repository.save(user);
    }

    @Transactional
    public void delete(Long userId) {
        LOGGER.debug("Deleting {}", userId);
        User existing = repository.findOne(userId);
        if (existing == null) {
            throw new ServiceException(
                    String.format("user with id =%s not exist", userId));
        }
        repository.delete(userId);
    }

    @Transactional(readOnly = true)
    public User get(Long userId) {
        LOGGER.debug("Retrieving the user : {}", userId);
        User user = repository.findOne(userId);
        if (user == null) {
            throw new ServiceException(
                    String.format("User with id=%s is not exist", userId));
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }


}
