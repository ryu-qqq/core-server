package com.ryuqq.core.api.controller.v1.category;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.category.request.CategorySearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.category.service.CategoryContextQueryService;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.enums.RequesterType;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class CategoryController {

	private final CategoryContextQueryService categoryContextQueryService;

	public CategoryController(CategoryContextQueryService categoryContextQueryService) {
		this.categoryContextQueryService = categoryContextQueryService;
	}

	@GetMapping("/category")
	public <T> ResponseEntity<ApiResponse<T>> fetchCategoryContext(
		@ModelAttribute CategorySearchConditionRequestDto categorySearchConditionRequestDto,
		@RequestHeader(value = "Requester-Type", required = false, defaultValue = "DEFAULT") String requesterType) {

		RequesterType type = RequesterType.valueOf(requesterType.toUpperCase());

		return ResponseEntity.ok(ApiResponse.success(categoryContextQueryService.fetchByConditionForRequester(categorySearchConditionRequestDto,
			type)));
	}

}
