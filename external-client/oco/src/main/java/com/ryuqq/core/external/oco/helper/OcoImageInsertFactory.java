package com.ryuqq.core.external.oco.helper;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.domain.product.core.ItemImage;
import com.ryuqq.core.external.oco.request.OcoImageInsertRequestDto;

public class OcoImageInsertFactory {

	public static List<OcoImageInsertRequestDto> toOcoImages(List<? extends ItemImage> itemImage){
		List<OcoImageInsertRequestDto> ocoImageInsertRequestDtos = new ArrayList<>();
		int imageSortCounter = 2;

		for (ItemImage img : itemImage) {
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
		if(ocoImageInsertRequestDtos.size() > 4) {
			ocoImageInsertRequestDtos = ocoImageInsertRequestDtos.subList(0, 4);
		}

		return ocoImageInsertRequestDtos;
	}
}
