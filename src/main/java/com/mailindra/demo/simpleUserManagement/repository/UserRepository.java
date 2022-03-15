package com.mailindra.demo.simpleUserManagement.repository;

import com.mailindra.demo.simpleUserManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
