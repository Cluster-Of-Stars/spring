package com.codereview.codereview.auth.contoller;

import com.codereview.codereview.auth.model.response.SkillResponse;
import com.codereview.codereview.auth.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill")
public class SkillController {

    private final SkillService skillService;
    @GetMapping
    public SkillResponse getSkills(){
        return skillService.getSkills();
    }

}
