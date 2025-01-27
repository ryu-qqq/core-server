package com.ryuqq.core.external.oco.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.external.oco.OcoOptionContext;
import com.ryuqq.core.external.oco.OcoOptionUpdateRequestContext;
import com.ryuqq.core.external.oco.helper.OcoOptionContextHelper;
import com.ryuqq.core.external.oco.request.OcoOptionInsertRequestDto;

@Component
public class OcoStockMapper {

	private final OcoOptionConverter optionMapper;
	private final OcoOptionMatcher ocoOptionMatcher;

	public OcoStockMapper(OcoOptionConverter optionMapper, OcoOptionMatcher ocoOptionMatcher) {
		this.optionMapper = optionMapper;
		this.ocoOptionMatcher = ocoOptionMatcher;
	}

	public OcoOptionUpdateRequestContext toOcoOptionUpdateRequestContext(long productGroupId, String externalProductGroupId){
		List<OcoOptionContext> ocoOptionContexts = optionMapper.generateOptionContext(productGroupId,
			externalProductGroupId);

		List<OcoOptionContext> updatedOptionContexts = ocoOptionMatcher.checkUpdates(ocoOptionContexts,
			externalProductGroupId);

		OcoOptionContextHelper.PartitionedOcoOptionContexts partitionedContexts =
			OcoOptionContextHelper.partitionContexts(updatedOptionContexts);


		List<OcoOptionInsertRequestDto> insertRequestDtos = partitionedContexts.getInserts().stream()
			.map(OcoOptionContext::ocoOptionInsertRequestDto)
			.toList();

		return new OcoOptionUpdateRequestContext(partitionedContexts.getInserts(), partitionedContexts.getDeleted());
	}



}
