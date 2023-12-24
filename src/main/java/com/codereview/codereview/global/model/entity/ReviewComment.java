package com.codereview.codereview.global.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @OneToMany(mappedBy = "reviewComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewCommentHeart> reviewCommentHearts;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    public void updateContent(String content){
        this.content = content;
    }

}
