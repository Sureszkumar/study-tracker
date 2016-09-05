package com.sample.repository;

import com.sample.domain.User;

public interface UserRepository extends BaseRepository<User> {

    User getUserByMobile(String email);
}
