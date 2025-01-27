package com.ryuqq.core.domain.external;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DataNotFoundException;

@Component
public class ExternalProductGroupContextMapper {

	public List<ExternalProductGroupContext> toDomain(Map<String, ExternalProductGroup> stringExternalProductGroupMap, List<ExternalProduct> externalProducts){
		Map<String, List<ExternalProduct>> externalProductGroupIdProductMap = groupByExternalProductGroupId(externalProducts);
		return externalProductGroupIdProductMap.entrySet().stream().map(entry ->
				{
					ExternalProductGroup externalProductGroup = stringExternalProductGroupMap.get(entry.getKey());
					if(externalProductGroup != null){
						return ExternalProductGroupContext.create(externalProductGroup, entry.getValue());
					}

					throw new DataNotFoundException("External Product Group not found for ID: " + entry.getKey());
		}).collect(Collectors.toList());
	}

	private Map<String, List<ExternalProduct>> groupByExternalProductGroupId(List<ExternalProduct> externalProductGroups){
		return externalProductGroups.stream().collect(Collectors.groupingBy(ExternalProduct::getExternalProductGroupId));

	}


}
