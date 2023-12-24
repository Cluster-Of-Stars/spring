package com.codereview.codereview.global.model.repository;

import com.codereview.codereview.global.model.entity.ReviewView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewViewRepository extends
        JpaRepository<ReviewView, Long>{
}