package com.codereview.codereview.domain.comment.model.response;

public record ReviewCommentResponse(
        Long commentId,
        String content,
        Long heartCount,
        Boolean heartCheck
) {
}
