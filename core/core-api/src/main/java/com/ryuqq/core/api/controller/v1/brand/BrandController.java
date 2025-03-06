package com.ryuqq.core.api.controller.v1.brand;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.brand.request.BrandSearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.brand.service.BrandContextQueryService;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.enums.RequesterType;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class BrandController {

	private final BrandContextQueryService brandContextQueryService;

	public BrandController(BrandContextQueryService brandContextQueryService) {
		this.brandContextQueryService = brandContextQueryService;
	}

	@GetMapping("/brand")
	public <T> ResponseEntity<ApiResponse<T>> fetchBrandContext(
		@ModelAttribute BrandSearchConditionRequestDto brandSearchConditionRequestDto,
		@RequestHeader(value = "Requester-Type", required = false, defaultValue = "DEFAULT") String requesterType) {

		RequesterType type = RequesterType.valueOf(requesterType.toUpperCase());

		return ResponseEntity.ok(ApiResponse.success(brandContextQueryService.fetchByConditionForRequester(brandSearchConditionRequestDto,
			type)));
	}

}
