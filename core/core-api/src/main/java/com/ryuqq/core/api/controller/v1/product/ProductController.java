package com.ryuqq.core.api.controller.v1.product;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductGroupSearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.core.api.controller.v1.product.response.ProductGroupInsertResponseDto;
import com.ryuqq.core.api.controller.v1.product.service.ProductGroupContextCommandService;
import com.ryuqq.core.api.controller.v1.product.service.ProductGroupContextQueryFacade;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.enums.RequesterType;

import jakarta.validation.Valid;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

	private final ProductGroupContextCommandService productGroupContextCommandService;
	private final ProductGroupContextQueryFacade productGroupContextQueryFacade;

	public ProductController(ProductGroupContextCommandService productGroupContextCommandService,
							 ProductGroupContextQueryFacade productGroupContextQueryFacade) {
		this.productGroupContextCommandService = productGroupContextCommandService;
		this.productGroupContextQueryFacade = productGroupContextQueryFacade;
	}

	@GetMapping("/product/group/{productGroupId}")
	public ResponseEntity<ApiResponse<ProductGroupContextResponse>> fetchProductGroupContext(
		@PathVariable("productGroupId") long productGroupId,
		@RequestHeader(value = "Requester-Type", required = false, defaultValue = "DEFAULT") String requesterType) {

		RequesterType type = RequesterType.valueOf(requesterType.toUpperCase());

		return ResponseEntity.ok(ApiResponse.success(
			productGroupContextQueryFacade.fetchByIdForRequester(productGroupId, type)
		));
	}

	@GetMapping("/product/group")
	public <T> ResponseEntity<ApiResponse<T>> fetchProductGroupContext(
		@ModelAttribute ProductGroupSearchConditionRequestDto productGroupSearchConditionRequestDto,
		@RequestHeader(value = "Requester-Type", required = false, defaultValue = "DEFAULT") String requesterType) {

		RequesterType type = RequesterType.valueOf(requesterType.toUpperCase());

		return ResponseEntity.ok(ApiResponse.success(productGroupContextQueryFacade.fetchByConditionForRequester(productGroupSearchConditionRequestDto,
			type)));
	}

	@PostMapping("/product/group")
	public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> registerProductGroup(@RequestBody @Valid ProductGroupContextCommandRequestDto productGroupContextCommandRequestDto){
		long productGroupId = productGroupContextCommandService.registerProductGroupContext(productGroupContextCommandRequestDto);
		return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(productGroupId)));
	}

	@PutMapping("/product/group/{productGroupId}")
	public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> updateProductGroup(@PathVariable("productGroupId") long productGroupId, @RequestBody @Valid ProductGroupContextCommandRequestDto productGroupContextCommandRequestDto){
		long updatedProductGroupId = productGroupContextCommandService.updateProductGroupContext(productGroupId, productGroupContextCommandRequestDto);
		return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(updatedProductGroupId)));
	}


}
