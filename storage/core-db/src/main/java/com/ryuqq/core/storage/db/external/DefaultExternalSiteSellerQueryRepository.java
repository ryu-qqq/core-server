package com.ryuqq.core.storage.db.external;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.domain.external.ExternalSiteSellerRelation;
import com.ryuqq.core.domain.external.dao.site.ExternalSiteQueryRepository;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultExternalSiteSellerQueryRepository implements ExternalSiteQueryRepository {

	private final ExternalSiteQueryDslRepository externalSiteQueryDslRepository;

	public DefaultExternalSiteSellerQueryRepository(ExternalSiteQueryDslRepository externalSiteQueryDslRepository) {
		this.externalSiteQueryDslRepository = externalSiteQueryDslRepository;
	}


	public Optional<ExternalSiteSellerRelation> fetchBySellerId(long sellerId) {
		return externalSiteQueryDslRepository.fetchBySellerId(sellerId)
			.map(e -> {
				List<ExternalSite> externalSites = e.getSites()
					.stream()
					.map(es -> new ExternalSite(es.getSiteId(), es.getSiteName()))
					.toList();
				return new ExternalSiteSellerRelation(e.getSellerId(), e.getSellerName(), externalSites);
			});
	}

	@Override
	public List<ExternalSiteSellerRelation> fetchExternalSiteSellerRelation() {
		return externalSiteQueryDslRepository.fetchExternalSiteSellerRelation().stream()
			.map(e -> {
				List<ExternalSite> externalSites = e.getSites()
					.stream()
					.map(es -> new ExternalSite(es.getSiteId(), es.getSiteName()))
					.toList();
				return new ExternalSiteSellerRelation(e.getSellerId(), e.getSellerName(), externalSites);
			})
			.toList();
	}

	@Override
	public ExternalSite fetchBySiteName(SiteName siteName) {
		return externalSiteQueryDslRepository.fetchBySiteName(siteName)
			.map(e -> new ExternalSite(e.getSiteId(), e.getSiteName()))
			.orElseThrow(() -> new DataNotFoundException(String.format("Site Not found Site Name : %s", siteName)));
	}

}
