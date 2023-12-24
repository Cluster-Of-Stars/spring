package com.codereview.codereview.global.model.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeReviewStatus {

    CODE_WAITING("코드리뷰 대기중"),
    CODE_ING("코드리뷰 중"),
    CODE_CLEAR("코드리뷰 완료");

    private final String status;
}
