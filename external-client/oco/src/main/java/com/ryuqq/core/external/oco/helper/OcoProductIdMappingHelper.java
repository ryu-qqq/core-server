package com.ryuqq.core.external.oco.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoProductIdMapping;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;

public class OcoProductIdMappingHelper {

	public static List<OcoProductIdMapping> toOcoProductIdMappingList(List<OcoOptionContext> ocoOptionContexts,
																	  OcoProductInsertResponseDto ocoProductInsertResponseDto) {

		Map<String, OcoProductInsertResponseDto.OptionList> responseOptionMap = ocoProductInsertResponseDto.optionList().stream()
			.collect(Collectors.toMap(
				option -> option.optionData1() + " " + option.optionData2(),
				option -> option
			));

		return ocoOptionContexts.stream()
			.map(optionContext -> {
				String key = optionContext.ocoOptionInsertRequestDto().optionData1() + " " +
					optionContext.ocoOptionInsertRequestDto().optionData2();

				OcoProductInsertResponseDto.OptionList matchedOption = responseOptionMap.get(key);

				if (matchedOption != null) {
					return new OcoProductIdMapping(
						optionContext.productId(),
						matchedOption.productOptionId(),
						key,
						optionContext.ocoOptionInsertRequestDto().quantity(),
						BigDecimal.valueOf(optionContext.ocoOptionInsertRequestDto().additionalPrice()),
						optionContext.ocoOptionInsertRequestDto().quantity() <= 0,
						true,
						false
					);
				}
				return null;
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toList());

	}


	public static List<OcoProductIdMapping> toOcoProductIdMappingListForDelete(List<OcoOptionContext> deletedOptionContexts) {
		return deletedOptionContexts.stream()
			.map(o -> new OcoProductIdMapping(
				o.productId(), o.ocoOptionInsertRequestDto().productOptionId(),
				o.ocoOptionInsertRequestDto().optionData1()
					+ " "
					+ o.ocoOptionInsertRequestDto().optionData2(),
				o.ocoOptionInsertRequestDto().quantity(),
				BigDecimal.valueOf(o.ocoOptionInsertRequestDto().additionalPrice()),
				o.ocoOptionInsertRequestDto().quantity() <= 0,
				false,
				false
			)).toList();

	}

}
