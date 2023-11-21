package com.codereview.codereview.global.model.entity;

import com.codereview.codereview.global.model.type.CodeReviewStatus;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
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
    private Long views;

    @Enumerated
    private CodeReviewStatus status;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

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

}
