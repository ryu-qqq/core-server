package com.ryuqq.core.api.controller.v1.external.mapper;

import com.ryuqq.core.api.controller.v1.external.request.BuyMaProductInsertFailedRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.BuyMaProductInsertedRequestDto;
import com.ryuqq.core.api.controller.v1.external.request.ExternalProductGroupStatusUpdateRequestDto;
import com.ryuqq.core.enums.SyncStatus;


public class BuyMaProductInsertedAdapter {

	public static ExternalProductGroupStatusUpdateRequestDto toExternalProductGroupStatusUpdateRequestDto(
		BuyMaProductInsertedRequestDto buyMaProductInsertedRequestDto){
		return new ExternalProductGroupStatusUpdateRequestDto(
			String.valueOf(buyMaProductInsertedRequestDto.id()),
			getProductGroupId(buyMaProductInsertedRequestDto.referenceNumber()),
			SyncStatus.APPROVED
		);
	}


	public static ExternalProductGroupStatusUpdateRequestDto toExternalProductGroupStatusUpdateRequestDto(
		BuyMaProductInsertFailedRequestDto buyMaProductInsertFailedRequestDto){
		return new ExternalProductGroupStatusUpdateRequestDto(
			buyMaProductInsertFailedRequestDto.requestUid(),
			null,
			SyncStatus.FAILED
		);
	}


	private static long getProductGroupId(String referenceNumber){
		String[] split = referenceNumber.split("_");
		if(split.length > 1){
			return Long.parseLong(split[0]);
		}else{
			return Long.parseLong(referenceNumber);
		}
	}

}
