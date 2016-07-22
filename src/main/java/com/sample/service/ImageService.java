package com.sample.service;

import com.sample.domain.Image;
import com.sample.domain.User;
import com.sample.repository.ImageRepository;
import com.sample.repository.UserRepository;
import com.sample.service.exception.ServiceException;
import com.sample.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    private final ImageRepository repository;

    @Inject
    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Image get(Long userId) {
        LOGGER.debug("Retrieving the user : {}", userId);
        Image user = repository.findOne(userId);
        if (user == null) {
            throw new ServiceException(
                    String.format("User with id=%s is not exist", userId));
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<Image> getAll() {
        LOGGER.debug("Retrieving the list of all Images");
        return repository.findAll();
    }

    @Transactional
    public Image uploadImage(MultipartFile image){
        Image imageEntity = new Image();
        try {
            imageEntity.setImage(image.getBytes());
        } catch (IOException e) {
            throw new ServiceException(
                    String.format("Image throw I/O exception"));
        }
        return repository.save(imageEntity);
    }


}
