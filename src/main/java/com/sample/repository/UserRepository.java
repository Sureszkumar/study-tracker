package com.sample.repository;

import com.sample.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile getUserByEmail(String email);
}
