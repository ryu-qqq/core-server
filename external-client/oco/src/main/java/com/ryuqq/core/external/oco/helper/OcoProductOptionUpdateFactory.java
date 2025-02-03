package com.ryuqq.core.external.oco.helper;

import java.util.List;

import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.request.OcoOptionDeleteRequestDto;
import com.ryuqq.core.external.oco.request.OcoOptionInsertRequestDto;
import com.ryuqq.core.external.oco.request.OcoOptionUpdateRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductOptionDeleteRequestDto;

public class OcoProductOptionUpdateFactory {

	public static OcoOptionUpdateRequestDto createOcoOptionUpdateRequestDto(String externalProductGroupId,  List<OcoOptionContext> updatedOptionContexts) {
		OcoOptionContextHelper.PartitionedOcoOptionContexts partitionedContexts =
			OcoOptionContextHelper.partitionContexts(updatedOptionContexts);

		List<OcoOptionInsertRequestDto> insertRequestDtos = partitionedContexts.getInserts().stream()
			.map(OcoOptionContext::ocoOptionInsertRequestDto)
			.toList();

		return new OcoOptionUpdateRequestDto(
			Long.parseLong(externalProductGroupId),
			insertRequestDtos
		);
	}

	public static OcoProductOptionDeleteRequestDto createOcoOptionDeleteRequestDto(String externalProductGroupId, List<OcoOptionContext> optionContext) {
		List<OcoOptionDeleteRequestDto> options = optionContext.stream().map(o -> new OcoOptionDeleteRequestDto(
			o.ocoOptionInsertRequestDto().productOptionId(),
			o.ocoOptionInsertRequestDto().pid()
		)).toList();

		return new OcoProductOptionDeleteRequestDto(
			Long.parseLong(externalProductGroupId),
			options
		);
	}


}
