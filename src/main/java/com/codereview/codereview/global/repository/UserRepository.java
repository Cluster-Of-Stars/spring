package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}