package com.codereview.codereview.domain.auth.service;

import com.codereview.codereview.domain.auth.model.response.CategoriesResponse;
import com.codereview.codereview.domain.auth.model.response.CategoryResponse;
import com.codereview.codereview.global.model.entity.Category;
import com.codereview.codereview.global.model.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity getSkills() {

        List<CategoryResponse> skillList = new ArrayList<>();

        for (Category s : categoryRepository.findAll()) {
            skillList.add(new CategoryResponse(s.getId(), s.getSkill(), s.getInfo()));
        }

        return ResponseEntity.ok().body(new CategoriesResponse(skillList));
    }

}
