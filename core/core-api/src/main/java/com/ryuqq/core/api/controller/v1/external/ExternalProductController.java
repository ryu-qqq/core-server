package com.ryuqq.core.api.controller.v1.external;

import static com.ryuqq.core.api.config.EndPointsConstants.BASE_END_POINT_V1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuqq.core.api.controller.v1.external.request.ExternalProductSyncRequestDto;
import com.ryuqq.core.api.controller.v1.external.service.ExternalProductGroupDomainService;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ExternalProductController {

	private final ExternalProductGroupDomainService externalProductGroupDomainService;

	public ExternalProductController(ExternalProductGroupDomainService externalProductGroupDomainService) {
		this.externalProductGroupDomainService = externalProductGroupDomainService;
	}

	@PostMapping("/external/product-sync")
	public void update(@RequestBody ExternalProductSyncRequestDto externalProductSyncRequestDto){
		externalProductGroupDomainService.syncExternalProductGroup(externalProductSyncRequestDto.siteId(), externalProductSyncRequestDto.status());
	}

}
