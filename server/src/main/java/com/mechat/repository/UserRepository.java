package com.mechat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Number> {
    Optional<UserEntity> findById(@Param("id") Number id);

    Optional<UserEntity> findByUsername(@Param("username") String username);
}
