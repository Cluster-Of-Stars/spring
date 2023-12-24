package com.codereview.codereview.domain.auth.model.response;

import java.util.List;

public record CategoriesResponse(
        List<CategoryResponse> categories
) {

}
