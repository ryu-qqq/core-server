package com.ryuqq.core.external.sellic.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ryuqq.core.domain.product.ProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductGroupImage;
import com.ryuqq.core.external.sellic.SellicImage;


public class SellicImageInsertFactory {

	public static List<SellicImage> toSellicImages(ProductGroupImageContext productGroupImageContext){
		List<SellicImage> sellicImages = new ArrayList<>();
		int imageSortCounter = 2;

		for (ProductGroupImage img : productGroupImageContext.getImages()) {
			if (img.getProductImageType().isMain()) {
				sellicImages.add(new SellicImage(1, img.getImageUrl()));
			} else {
				sellicImages.add(new SellicImage(imageSortCounter, img.getImageUrl()));
				imageSortCounter++;
			}
		}

		List<SellicImage> ordered = sellicImages.stream()
			.sorted(Comparator.comparing(SellicImage::order))
			.toList();

		if(ordered.size() > 5) {
			ordered = ordered.subList(0, 5);
		}

		return ordered;
	}
}
