package com.codereview.codereview.global.model.repository;

import com.codereview.codereview.global.model.entity.Category;
import com.codereview.codereview.global.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends
        JpaRepository<Review, Long>{
    Page<Review> findByCategory(Category category, Pageable pageable);

}