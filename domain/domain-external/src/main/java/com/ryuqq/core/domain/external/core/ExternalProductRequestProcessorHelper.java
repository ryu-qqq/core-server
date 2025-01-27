package com.ryuqq.core.domain.external.core;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ExternalProductRequestProcessorHelper {

	public static void processBySites(
		List<ExternalSite> sites,
		List<ExternalProductGroup> externalProductGroups,
		ProductDomainEventType productDomainEventType,
		ExternalProductRequestProcessorProvider processorProvider
	) {
		Map<Long, List<ExternalProductGroup>> groupedBySiteId = groupBySiteId(externalProductGroups);

		sites.forEach(site -> {
			List<ExternalProductGroup> filteredGroups = groupedBySiteId.getOrDefault(site.siteId(), List.of());

			if (!filteredGroups.isEmpty()) {
				SiteRequestProcessor processor = processorProvider.getProcessor(site);
				filteredGroups.forEach(f -> processor.process(productDomainEventType, f));

			}
		});
	}

	private static Map<Long, List<ExternalProductGroup>> groupBySiteId(
		List<ExternalProductGroup> externalProductGroups
	) {
		return externalProductGroups.stream()
			.collect(Collectors.groupingBy(ExternalProductGroup::getSiteId));
	}

}
