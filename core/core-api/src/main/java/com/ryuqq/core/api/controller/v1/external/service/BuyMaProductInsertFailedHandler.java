package com.ryuqq.core.api.controller.v1.external.service;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.external.mapper.BuyMaProductInsertedAdapter;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaProductInsertFailedRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.ExternalProductGroupStatusUpdateRequestDto;
import com.ryuqq.core.api.controller.v1.external.response.BuyMaProductInsertFailedResponseDto;
import com.ryuqq.core.domain.external.ExternalProductGroupStatusUpdater;
import com.ryuqq.core.enums.SiteName;

@Component
public class BuyMaProductInsertFailedHandler implements BuyMaWebhookHandler<BuyMaProductInsertFailedRequestDto, BuyMaProductInsertFailedResponseDto>{

	private final ExternalProductGroupStatusUpdater externalProductGroupStatusUpdater;

	public BuyMaProductInsertFailedHandler(ExternalProductGroupStatusUpdater externalProductGroupStatusUpdater) {
		this.externalProductGroupStatusUpdater = externalProductGroupStatusUpdater;
	}

	@Override
	public BuyMaProductInsertFailedResponseDto handle(BuyMaProductInsertFailedRequestDto requestDto) {

		ExternalProductGroupStatusUpdateRequestDto externalProductGroupStatusUpdateRequestDto =
			BuyMaProductInsertedAdapter.toExternalProductGroupStatusUpdateRequestDto(requestDto);

		long updatedId = externalProductGroupStatusUpdater.updateStatus(
			externalProductGroupStatusUpdateRequestDto.productGroupId(),
			SiteName.BUYMA,
			externalProductGroupStatusUpdateRequestDto.externalProductGroupId(),
			externalProductGroupStatusUpdateRequestDto.syncStatus()
		);

		return new BuyMaProductInsertFailedResponseDto(updatedId);
	}

	@Override
	public boolean canHandle(Class<? extends BuyMaEventRequestDto> dtoClass) {
		return BuyMaProductInsertFailedRequestDto.class.equals(dtoClass);

	}
}
