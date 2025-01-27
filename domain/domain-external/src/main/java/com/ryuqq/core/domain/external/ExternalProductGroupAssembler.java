package com.ryuqq.core.domain.external;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ExternalProductGroupAssembler {

	private final ExternalProductGroupFinder externalProductGroupFinder;
	private final ExternalProductFinder externalProductFinder;
	private final ExternalProductGroupContextMapper externalProductGroupContextMapper;

	public ExternalProductGroupAssembler(ExternalProductGroupFinder externalProductGroupFinder,
										 ExternalProductFinder externalProductFinder,
										 ExternalProductGroupContextMapper externalProductGroupContextMapper) {
		this.externalProductGroupFinder = externalProductGroupFinder;
		this.externalProductFinder = externalProductFinder;
		this.externalProductGroupContextMapper = externalProductGroupContextMapper;
	}

	public List<ExternalProductGroupContext> assemble(long productGroupId) {
		List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchByProductGroupId(
			productGroupId);

		Map<String, ExternalProductGroup> stringExternalProductGroupMap = groupByExternalProductGroupId(
			externalProductGroups);

		List<ExternalProduct> externalProducts = externalProductFinder.fetchByExternalProductGroupIds(
			new ArrayList<>(stringExternalProductGroupMap.keySet()));

		return externalProductGroupContextMapper.toDomain(stringExternalProductGroupMap, externalProducts);
	}

	public List<ExternalProductGroupContext> assemble(List<Long> productGroupIds) {
		List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchByProductGroupIds(
			productGroupIds);

		Map<String, ExternalProductGroup> stringExternalProductGroupMap = groupByExternalProductGroupId(
			externalProductGroups);

		List<ExternalProduct> externalProducts = externalProductFinder.fetchByExternalProductGroupIds(
			new ArrayList<>(stringExternalProductGroupMap.keySet()));

		return externalProductGroupContextMapper.toDomain(stringExternalProductGroupMap, externalProducts);
	}





	private Map<String, ExternalProductGroup> groupByExternalProductGroupId(List<ExternalProductGroup> externalProductGroups) {
		return externalProductGroups.stream().collect(
			Collectors.toMap(ExternalProductGroup::getExternalProductGroupId, Function.identity(), (v1, v2) -> v1));
	}



}
