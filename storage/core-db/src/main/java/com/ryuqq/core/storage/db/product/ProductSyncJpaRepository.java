package com.ryuqq.core.storage.db.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSyncJpaRepository extends JpaRepository<ProductSyncEntity, Long> {

	Optional<ProductSyncEntity> findByProductGroupId(long productGroupId);

}
