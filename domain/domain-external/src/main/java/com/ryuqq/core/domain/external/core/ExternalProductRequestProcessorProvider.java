package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.enums.SiteName;

@Service
public class ExternalProductRequestProcessorProvider {

	private final List<SiteRequestProcessor> siteProcessors;

	public ExternalProductRequestProcessorProvider(List<SiteRequestProcessor> siteProcessors) {
		this.siteProcessors = siteProcessors;
	}

	public SiteRequestProcessor getProcessor(SiteName siteName) {
		return siteProcessors.stream()
			.filter(processor -> processor.supportsSite(siteName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No processor found for site: " + siteName));
	}


}
