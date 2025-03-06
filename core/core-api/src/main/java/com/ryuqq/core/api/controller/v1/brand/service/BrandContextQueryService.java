package com.ryuqq.core.api.controller.v1.brand.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuqq.core.api.controller.v1.brand.mapper.BrandContextResponseMapperProvider;
import com.ryuqq.core.api.controller.v1.brand.request.BrandSearchConditionRequestDto;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.brand.core.BrandQueryInterface;
import com.ryuqq.core.domain.brand.core.BrandSearchCondition;
import com.ryuqq.core.enums.RequesterType;

@Transactional(readOnly = true)
@Service
public class BrandContextQueryService {

	private final BrandContextResponseMapperProvider brandContextResponseMapperProvider;
	private final BrandQueryInterface brandQueryInterface;

	public BrandContextQueryService(BrandContextResponseMapperProvider brandContextResponseMapperProvider,
									BrandQueryInterface brandQueryInterface) {
		this.brandContextResponseMapperProvider = brandContextResponseMapperProvider;
		this.brandQueryInterface = brandQueryInterface;
	}

	public <T> T fetchByConditionForRequester(
		BrandSearchConditionRequestDto brandSearchConditionRequestDto, RequesterType requesterType){
		BrandSearchCondition brandSearchCondition = brandSearchConditionRequestDto.toBrandSearchCondition();

		long count = brandQueryInterface.countByCondition(brandSearchCondition);

		if(count ==0) return (T) List.of();

		List<? extends Brand> brands = brandQueryInterface.fetchByCondition(brandSearchCondition);

		return (T) brandContextResponseMapperProvider
			.getMapper(requesterType)
			.toResponseDto(
				brands,
				brandSearchCondition.getSize(),
				count
			);
	}

}
