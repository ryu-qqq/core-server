package com.ryuqq.core.api.controller.v1.external.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuqq.core.api.controller.v1.external.mapper.BuyMaProductInsertedAdapter;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaEventRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaProductInsertedRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.ExternalProductGroupStatusUpdateRequestDto;
import com.ryuqq.core.api.controller.v1.external.response.BuyMaProductInsertedResponseDto;
import com.ryuqq.core.domain.external.ExternalProductGroupStatusUpdater;
import com.ryuqq.core.enums.SiteName;

@Transactional
@Service
public class BuyMaProductInsertedHandler implements BuyMaWebhookHandler<BuyMaProductInsertedRequestDto, BuyMaProductInsertedResponseDto>{

	private final ExternalProductGroupStatusUpdater externalProductGroupStatusUpdater;

	public BuyMaProductInsertedHandler(ExternalProductGroupStatusUpdater externalProductGroupStatusUpdater) {
		this.externalProductGroupStatusUpdater = externalProductGroupStatusUpdater;
	}

	@Override
	public BuyMaProductInsertedResponseDto handle(BuyMaProductInsertedRequestDto requestDto) {

		ExternalProductGroupStatusUpdateRequestDto externalProductGroupStatusUpdateRequestDto =
			BuyMaProductInsertedAdapter.toExternalProductGroupStatusUpdateRequestDto(requestDto);

		long updatedId = externalProductGroupStatusUpdater.updateStatus(
			externalProductGroupStatusUpdateRequestDto.productGroupId(),
			SiteName.BUYMA,
			externalProductGroupStatusUpdateRequestDto.externalProductGroupId(),
			externalProductGroupStatusUpdateRequestDto.syncStatus()
		);

		return new BuyMaProductInsertedResponseDto(updatedId);
	}

	@Override
	public boolean canHandle(Class<? extends BuyMaEventRequestDto> dtoClass) {
		return BuyMaProductInsertedRequestDto.class.equals(dtoClass);

	}
}
