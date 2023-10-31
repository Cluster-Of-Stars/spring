package com.codereview.codereview.global.model.entity;

import com.codereview.codereview.global.model.type.Rank;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class User extends TimeStampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column( unique = true, nullable = false)
    private String userPw;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Enumerated
    private Rank rank;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    private List<String> skills;

}
