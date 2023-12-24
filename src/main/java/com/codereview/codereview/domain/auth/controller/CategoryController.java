package com.codereview.codereview.domain.auth.controller;

import com.codereview.codereview.domain.auth.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill")
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity getSkills(){
        return categoryService.getSkills();
    }

}
