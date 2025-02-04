package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalSite;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
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
