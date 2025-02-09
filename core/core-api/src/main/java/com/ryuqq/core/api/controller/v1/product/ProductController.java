package com.ryuqq.core.api.controller.v1.product;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.controller.v1.product.response.ProductGroupInsertResponseDto;
import com.ryuqq.core.api.controller.v1.product.service.ProductGroupContextCommandService;
import com.ryuqq.core.api.controller.v1.product.service.ProductGroupContextQueryService;
import com.ryuqq.core.api.payload.ApiResponse;
import com.ryuqq.core.domain.product.core.ProductGroupContext;

import jakarta.validation.Valid;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

	private final ProductGroupContextCommandService productGroupContextCommandService;
	private final ProductGroupContextQueryService productGroupContextQueryService;

	public ProductController(ProductGroupContextCommandService productGroupContextCommandService,
							 ProductGroupContextQueryService productGroupContextQueryService) {
		this.productGroupContextCommandService = productGroupContextCommandService;
		this.productGroupContextQueryService = productGroupContextQueryService;
	}


	@GetMapping("/product/group/{productGroupId}")
	public ResponseEntity<ApiResponse<ProductGroupContext>> fetchProductGroupContext(@PathVariable("productGroupId") long productGroupId){
		return ResponseEntity.ok(ApiResponse.success(productGroupContextQueryService.fetchById(productGroupId)));
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
