package com.mechat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);
}
