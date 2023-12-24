package com.codereview.codereview.domain.comment.model.response;

public record ReviewCommentResponse(
        String content,
        Long heartCount,
        Boolean heartCheck
) {
}
