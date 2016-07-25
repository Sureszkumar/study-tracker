package com.sample.repository;


import com.sample.domain.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseDomain> extends JpaRepository<T, Long>{
}
