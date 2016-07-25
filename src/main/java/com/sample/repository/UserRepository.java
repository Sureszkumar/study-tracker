package com.sample.repository;

import com.sample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends BaseRepository<User> {

    User getUserByMobile(String email);
}
