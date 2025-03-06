package com.ryuqq.core.external.oco.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ryuqq.core.domain.product.ProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductGroupImage;
import com.ryuqq.core.external.oco.request.OcoImageInsertRequestDto;

public class OcoImageInsertFactory {

	public static List<OcoImageInsertRequestDto> toOcoImages(ProductGroupImageContext productGroupImageContext){
		List<OcoImageInsertRequestDto> ocoImageInsertRequestDtos = new ArrayList<>();
		int imageSortCounter = 2;

		for (ProductGroupImage img : productGroupImageContext.getImages()) {
			if (img.getProductImageType().isMain()) {
				ocoImageInsertRequestDtos.add(new OcoImageInsertRequestDto(
					img.getImageUrl(),
					1
				));
			} else {
				ocoImageInsertRequestDtos.add(new OcoImageInsertRequestDto(
					img.getImageUrl(),
					imageSortCounter
				));
				imageSortCounter++;
			}
		}

		List<OcoImageInsertRequestDto> ordered = ocoImageInsertRequestDtos.stream()
			.sorted(Comparator.comparing(OcoImageInsertRequestDto::order))
			.toList();

		if(ordered.size() > 4) {
			ordered = ordered.subList(0, 4);
		}


		return ordered;
	}
}
