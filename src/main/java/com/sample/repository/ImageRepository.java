package com.sample.repository;

import com.sample.domain.Image;
import com.sample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
