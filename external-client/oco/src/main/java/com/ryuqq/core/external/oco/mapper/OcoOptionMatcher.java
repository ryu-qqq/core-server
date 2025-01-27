package com.ryuqq.core.external.oco.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalProductQueryInterface;
import com.ryuqq.core.domain.external.core.ExternalSku;
import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.request.OcoOptionInsertRequestDto;

@Component
public class OcoOptionMatcher {

	private final ExternalProductQueryInterface externalProductQueryInterface;

	public OcoOptionMatcher(ExternalProductQueryInterface externalProductQueryInterface) {
		this.externalProductQueryInterface = externalProductQueryInterface;
	}

	public List<OcoOptionContext> checkUpdates(List<OcoOptionContext> ocoOptionContexts, String externalProductGroupId){
		List<? extends ExternalSku> externalSkus = externalProductQueryInterface.fetchByExternalItemId(
			externalProductGroupId);

		Map<String, ExternalSku> existingOptionsMap = externalSkus.stream()
			.collect(Collectors.toMap(ExternalSku::getOptionValue, sku -> sku));

		List<OcoOptionContext> result = new ArrayList<>();

		for (OcoOptionContext ocoOptionContext : ocoOptionContexts) {
			String optionValue = ocoOptionContext.ocoOptionInsertRequestDto().optionData1() + " " +
				ocoOptionContext.ocoOptionInsertRequestDto().optionData2();

			if (existingOptionsMap.containsKey(optionValue)) {
				ExternalSku matchedSku = existingOptionsMap.get(optionValue);

				OcoOptionInsertRequestDto updatedRequestDto = new OcoOptionInsertRequestDto(
					matchedSku.getExternalProductId() != null ? Integer.parseInt(matchedSku.getExternalProductId()) : null,
					matchedSku.getExternalProductGroupId() != null ? Integer.parseInt(matchedSku.getExternalProductGroupId()) : null,
					ocoOptionContext.ocoOptionInsertRequestDto().optionData1(),
					ocoOptionContext.ocoOptionInsertRequestDto().optionData2(),
					ocoOptionContext.ocoOptionInsertRequestDto().quantity(),
					ocoOptionContext.ocoOptionInsertRequestDto().optionUse(),
					ocoOptionContext.ocoOptionInsertRequestDto().additionalPrice()
				);

				result.add(new OcoOptionContext(
					matchedSku.getProductId(),
					updatedRequestDto,
					false
				));

				existingOptionsMap.remove(optionValue);
			} else {
				result.add(ocoOptionContext);
			}
		}

		for (ExternalSku remainingSku : existingOptionsMap.values()) {
			OcoOptionInsertRequestDto deletedRequestDto = new OcoOptionInsertRequestDto(
				remainingSku.getExternalProductId() != null ? Integer.parseInt(remainingSku.getExternalProductId()) : null,
				remainingSku.getExternalProductGroupId() != null ? Integer.parseInt(remainingSku.getExternalProductGroupId()) : null,
				null,
				null,
				0,
				"N",
				0
			);

			result.add(new OcoOptionContext(
				remainingSku.getProductId(),
				deletedRequestDto,
				true
			));
		}

		return result;
	}



}
