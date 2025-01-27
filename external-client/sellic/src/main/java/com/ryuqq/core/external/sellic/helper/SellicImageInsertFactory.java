package com.ryuqq.core.external.sellic.helper;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.domain.product.core.ItemImage;
import com.ryuqq.core.external.sellic.SellicImage;


public class SellicImageInsertFactory {

	public static List<SellicImage> toSellicImages(List<? extends ItemImage> itemImages){
		List<SellicImage> sellicImages = new ArrayList<>();
		int imageSortCounter = 2;

		for (ItemImage img : itemImages) {
			if (img.getProductImageType().isMain()) {
				sellicImages.add(new SellicImage(1,img.getImageUrl()));
			} else {
				sellicImages.add(new SellicImage(imageSortCounter, img.getImageUrl()));
				imageSortCounter++;
			}
		}
		if(sellicImages.size() > 4) {
			sellicImages = sellicImages.subList(0, 4);
		}

		return sellicImages;
	}
}
