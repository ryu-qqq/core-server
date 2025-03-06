package com.ryuqq.core.storage.db.seller;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.seller.dao.SellerQueryRepository;
import com.ryuqq.core.domain.seller.dao.SellerSnapshot;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultSellerQueryRepository implements SellerQueryRepository {

	private final SellerQueryDslRepository sellerQueryDslRepository;

	public DefaultSellerQueryRepository(SellerQueryDslRepository sellerQueryDslRepository) {
		this.sellerQueryDslRepository = sellerQueryDslRepository;
	}

	@Override
	public boolean existById(long id) {
		return sellerQueryDslRepository.existById(id);
	}

	@Override
	public SellerSnapshot fetchById(long id) {
		return sellerQueryDslRepository.fetchById(id)
			.map(SellerSnapshotMapper::toSnapshot)
			.orElseThrow(() -> new DataNotFoundException(String.format("Seller Not found seller Id : %s", id)));
	}

	@Override
	public List<? extends SellerSnapshot> fetchByIds(List<Long> ids) {
		return sellerQueryDslRepository.fetchByIds(ids).stream()
			.map(SellerSnapshotMapper::toSnapshot)
			.toList();
	}
}
