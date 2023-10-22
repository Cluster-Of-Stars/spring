package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findBySkill(String skill);

}