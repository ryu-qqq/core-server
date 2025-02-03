package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalSite;

@Component
public class ExternalProductRequestProcessorProvider {

	private final List<SiteRequestProcessor> siteProcessors;

	public ExternalProductRequestProcessorProvider(List<SiteRequestProcessor> siteProcessors) {
		this.siteProcessors = siteProcessors;
	}

	public SiteRequestProcessor getProcessor(ExternalSite site) {
		return siteProcessors.stream()
			.filter(processor -> processor.supportsSite(site))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No processor found for site: " + site.siteName()));
	}


}
