package com.codereview.codereview.auth.service;

import com.codereview.codereview.auth.model.response.SkillResponse;
import com.codereview.codereview.global.model.entity.Skill;
import com.codereview.codereview.global.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillResponse getSkills() {

        List<String> skillList = new ArrayList<>();

        for (Skill s : skillRepository.findAll()) {
            skillList.add(s.getSkill());
        }

        return new SkillResponse(skillList);
    }

}
