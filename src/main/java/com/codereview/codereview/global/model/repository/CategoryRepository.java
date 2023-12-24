package com.codereview.codereview.global.model.repository;

import com.codereview.codereview.global.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findBySkill(String skill);

}