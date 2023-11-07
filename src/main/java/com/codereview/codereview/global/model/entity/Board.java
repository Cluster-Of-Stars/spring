package com.codereview.codereview.global.model.entity;

import com.codereview.codereview.global.model.type.CodeReviewStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class Board extends TimeStampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String problem;

    @Column
    private String question;

    @Column
    private String code;

    @Column
    private long views;

    @Enumerated
    private CodeReviewStatus status;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    private List<String> category;

    @ManyToOne( cascade = CascadeType.ALL)
    private User user;

}
