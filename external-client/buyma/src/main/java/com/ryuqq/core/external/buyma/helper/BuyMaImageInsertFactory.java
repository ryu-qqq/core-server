package com.ryuqq.core.external.buyma.helper;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.domain.product.core.ItemImage;
import com.ryuqq.core.external.buyma.request.BuyMaImageInsertRequestDto;


public class BuyMaImageInsertFactory {

	public static List<BuyMaImageInsertRequestDto> toBuyMaImages(List<? extends ItemImage> itemImage){
		List<BuyMaImageInsertRequestDto> buyMaImageInsertRequests = new ArrayList<>();
		int imageSortCounter = 2;

		for (ItemImage img : itemImage) {
			if (img.getProductImageType().isMain()) {
				buyMaImageInsertRequests.add(new BuyMaImageInsertRequestDto(
					img.getImageUrl(),
					1
				));
			} else {
				buyMaImageInsertRequests.add(new BuyMaImageInsertRequestDto(
					img.getImageUrl(),
					imageSortCounter
				));
				imageSortCounter++;
			}
		}

		return buyMaImageInsertRequests;
	}


}
