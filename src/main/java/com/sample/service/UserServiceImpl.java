package com.sample.service;

import com.sample.domain.User;
import com.sample.repository.UserRepository;
import com.sample.service.exception.UserServiceException;
import com.sample.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User createUser(@NotNull final User user) {
        LOGGER.debug("Creating {}", user);
        User existing = repository.getUserByMobile(user.getMobile());
        if (existing != null) {
            throw new UserServiceException(
                    String.format("There already exists a user with mobile =%s", user.getMobile()));
        }
        user.setCreationDateTime(LocalDateTime.now());
        user.setLastChangeTimestamp(LocalDateTime.now());
        User newUser = repository.save(user);
        newUser.setAuthToken(ServiceUtils.createAuthToken(newUser.getId()));
        User savedUser = repository.save(newUser);
        return savedUser;
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        LOGGER.debug("Deleting {}", userId);
        User existing = repository.findOne(userId);
        if (existing == null) {
            throw new UserServiceException(
                    String.format("user with id =%s not exist", userId));
        }
        repository.delete(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long userId) {
        LOGGER.debug("Retrieving the user : {}", userId);
        User user = repository.findOne(userId);
        if (user == null) {
            throw new UserServiceException(
                    String.format("User with id=%s is not exist", userId));
        }
        return user;
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String mobile) {
        LOGGER.debug("Retrieving the user by mobile : {}", mobile);
        User user = repository.getUserByMobile(mobile);
        if (user == null) {
            throw new UserServiceException(
                    String.format("User with id=%s is not exist", mobile));
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }


}
