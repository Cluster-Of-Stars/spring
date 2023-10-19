package com.codereview.codereview.global.model.entity;

import com.codereview.codereview.global.model.type.Rank;
import com.codereview.codereview.global.model.type.TimeStampEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends TimeStampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(length = 20, unique = true, nullable = false)
    private String userId;

    @Column(length = 20, unique = true, nullable = false)
    private String userPw;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Enumerated
    private Rank rank;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Skill> skills;

}
