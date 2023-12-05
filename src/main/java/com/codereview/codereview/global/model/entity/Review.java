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
public class Review extends TimeStampEntity {

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
    private Long views;

    @Enumerated
    private CodeReviewStatus status;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewHeart> reviewHearts;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewView> reviewViews;

    public void updateCodeReview(
            String title,
            String code,
            String question,
            String problem
    ) {
        this.title = title;
        this.code = code;
        this.problem = problem;
        this.question = question;
    }

    public void updateCodeReview(
            CodeReviewStatus status
    ){
        this.status = status;
    }

}
