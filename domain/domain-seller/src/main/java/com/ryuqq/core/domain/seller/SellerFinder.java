package com.ryuqq.core.domain.seller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.domain.seller.core.SellerQueryInterface;
import com.ryuqq.core.domain.seller.dao.SellerQueryRepository;
import com.ryuqq.core.domain.seller.dao.SellerSnapshot;

@Component
class SellerFinder implements SellerQueryInterface {

	private final SellerQueryRepository sellerQueryRepository;

	SellerFinder(SellerQueryRepository sellerQueryRepository) {
		this.sellerQueryRepository = sellerQueryRepository;
	}

	@Override
	public boolean existById(long id) {
		return sellerQueryRepository.existById(id);
	}

	@Override
	public Seller fetchById(long id) {
		SellerSnapshot sellerSnapshot = sellerQueryRepository.fetchById(id);
		return SellerMapper.toSeller(sellerSnapshot);
	}

	@Override
	public List<? extends Seller> fetchByIds(List<Long> ids) {
		return sellerQueryRepository.fetchByIds(ids).stream()
			.map(SellerMapper::toSeller)
			.toList();
	}

}
