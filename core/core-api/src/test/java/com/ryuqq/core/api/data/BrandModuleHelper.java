package com.ryuqq.core.api.data;

import java.util.List;

import com.ryuqq.core.api.controller.v1.brand.response.DefaultBrandContextResponseDto;
import com.ryuqq.core.domain.brand.DefaultBrand;
import com.ryuqq.core.domain.brand.core.Brand;

public class BrandModuleHelper {

	public static Brand createBrand(){
		return new DefaultBrand(4856L, "Moncler", "몽클레어", true);
	}

	public static List<? extends Brand> createBrands(){
		return List.of(
		 	new DefaultBrand(4856, "Moncler", "몽클레어", true),
			new DefaultBrand(4857, "Murad", "뮤라드", true),
			new DefaultBrand(4858, "NANOS", "나노스", true),
			new DefaultBrand(4859, "MONOTHEME", "모노템", true),
			new DefaultBrand(4860, "MONDAINE", "몬데인", true)
		);
	}

	public static List<DefaultBrandContextResponseDto> createBrandContextResponseDtos(){
		return createBrands().stream()
			.map(b -> new DefaultBrandContextResponseDto(
				b.id(),
				b.brandName(),
				b.brandNameKr(),
				b.displayed()
			)).toList();
	}

}
