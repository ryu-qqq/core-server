package com.ryuqq.core.api.controller.v1.external;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.ExternalProductSyncRequestDto;
import com.ryuqq.core.api.controller.v1.external.service.BuyMaWebhookHandler;
import com.ryuqq.core.api.controller.v1.external.service.BuyMaWebhookHandlerProvider;
import com.ryuqq.core.api.controller.v1.external.service.ExternalProductGroupCommandService;
import com.ryuqq.core.api.payload.ApiResponse;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ExternalProductController {

	private final ExternalProductGroupCommandService externalProductGroupCommandService;
	private final BuyMaWebhookHandlerProvider buyMaWebhookHandlerProvider;

	public ExternalProductController(ExternalProductGroupCommandService externalProductGroupCommandService,
									 BuyMaWebhookHandlerProvider buyMaWebhookHandlerProvider) {
		this.externalProductGroupCommandService = externalProductGroupCommandService;
		this.buyMaWebhookHandlerProvider = buyMaWebhookHandlerProvider;
	}

	@PostMapping("/external/product-sync")
	public void update(@RequestBody ExternalProductSyncRequestDto externalProductSyncRequestDto){
		externalProductGroupCommandService.syncExternalProductGroup(externalProductSyncRequestDto.siteId(), externalProductSyncRequestDto.status(), externalProductSyncRequestDto.productDomainEventType(), externalProductSyncRequestDto.size());
	}


	@PostMapping("/external/buyma/webhook")
	public <T extends BuyMaEventRequestDto> ResponseEntity<ApiResponse<?>> syncWebHookProduct(T requestDto){
		@SuppressWarnings("unchecked")
		BuyMaWebhookHandler<T, ?> handler = (BuyMaWebhookHandler<T, ?>) buyMaWebhookHandlerProvider.getHandler(requestDto.getClass());
		return ResponseEntity.ok(ApiResponse.success(handler.handle(requestDto)));
	}


}
