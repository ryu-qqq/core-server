package com.ryuqq.core.storage.db.external;


import com.ryuqq.core.enums.Origin;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.enums.SiteType;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "SITE")
@Entity
public class SiteEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 100)
	@Enumerated(EnumType.STRING)
    private SiteName name;

    @Column(name = "BASE_URL", nullable = false, length = 255)
    private String baseUrl;

    @Column(name = "COUNTRY_CODE", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private Origin countryCode;

    @Column(name = "SITE_TYPE", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SiteType siteType;

    protected SiteEntity() {}

    public SiteEntity(SiteName name, String baseUrl, Origin countryCode, SiteType siteType) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.countryCode = countryCode;
        this.siteType = siteType;
    }

	public SiteName getName() {
		return name;
	}

	public String getBaseUrl() {
        return baseUrl;
    }

    public Origin getCountryCode() {
        return countryCode;
    }

    public SiteType getSiteType() {
        return siteType;
    }
}
