package com.ryuqq.core.storage.db.external;

import java.time.LocalDateTime;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_SITE_SELLER")
@Entity
public class ExternalSiteSellerEntity extends BaseEntity {

    @Column(name = "SELLER_ID", nullable = false)
    private long sellerId;

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

	@Column(name = "SELLER_NAME", nullable = false)
	private String sellerName;

    @Column(name = "ACTIVE_STATUS", nullable = false)
    private boolean activeStatus;

    @Column(name = "REGISTRATION_START_TIME", nullable = false)
    private LocalDateTime registrationStartTime;

    @Column(name = "REGISTRATION_END_TIME", nullable = false)
    private LocalDateTime registrationEndTime;

    protected ExternalSiteSellerEntity() {}


	public ExternalSiteSellerEntity(long sellerId, long siteId, String sellerName, boolean activeStatus,
									LocalDateTime registrationStartTime, LocalDateTime registrationEndTime) {
		this.sellerId = sellerId;
		this.siteId = siteId;
		this.sellerName = sellerName;
		this.activeStatus = activeStatus;
		this.registrationStartTime = registrationStartTime;
		this.registrationEndTime = registrationEndTime;
	}



}
