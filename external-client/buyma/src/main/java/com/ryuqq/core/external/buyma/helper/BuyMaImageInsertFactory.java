package com.ryuqq.core.external.buyma.helper;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.domain.product.core.ProductGroupImage;
import com.ryuqq.core.domain.product.core.ProductGroupImageContext;
import com.ryuqq.core.external.buyma.request.BuyMaImageInsertRequestDto;


public class BuyMaImageInsertFactory {

	public static List<BuyMaImageInsertRequestDto> toBuyMaImages(ProductGroupImageContext productGroupImageContext){
		List<BuyMaImageInsertRequestDto> buyMaImageInsertRequests = new ArrayList<>();
		int imageSortCounter = 2;

		for (ProductGroupImage img : productGroupImageContext.getImages()) {
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
