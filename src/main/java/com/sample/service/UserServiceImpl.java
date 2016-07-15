package com.sample.service;

import com.sample.domain.UserProfile;
import com.sample.repository.UserRepository;
import com.sample.service.exception.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public UserProfile save(@NotNull @Valid final UserProfile userProfile) {
        LOGGER.debug("Creating {}", userProfile);
        UserProfile existing = repository.getUserByEmail(userProfile.getEmail());
        if (existing != null) {
            throw new UserServiceException(
                    String.format("There already exists a user with email =%s", userProfile.getEmail()));
        }

        return repository.save(userProfile);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        LOGGER.debug("Deleting {}", userId);
        UserProfile existing = repository.findOne(userId);
        if (existing == null) {
            throw new UserServiceException(
                    String.format("user with id =%s not exist", userId));
        }
        repository.delete(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile getUser(Long userId) {
        LOGGER.debug("Retrieving the user : {}", userId);
        UserProfile userProfile = repository.findOne(userId);
        if (userProfile == null) {
            throw new UserServiceException(
                    String.format("User with id=%s is not exist", userId));
        }
        return userProfile;
    }
    @Override
    @Transactional(readOnly = true)
    public UserProfile getUserByEmail(String email) {
        LOGGER.debug("Retrieving the user by email : {}", email);
        UserProfile userProfile = repository.getUserByEmail(email);
        if (userProfile == null) {
            throw new UserServiceException(
                    String.format("User with id=%s is not exist", email));
        }
        return userProfile;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }


}
