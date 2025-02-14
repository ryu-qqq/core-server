package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductGroupImage;
import com.ryuqq.core.domain.product.core.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.core.ProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductGroupImageChecker implements UpdateChecker<ProductGroupImageContext, ProductGroupImageContextCommand> {

	@Override
	public void checkUpdates(UpdateDecision decision, ProductGroupImageContext existing, ProductGroupImageContextCommand updated) {
		List<ProductGroupImageCommand> changedImages = new ArrayList<>();

		Map<String, ProductGroupImage> existingMap = mapByImageUrl(existing);

		processUpdatedImages(existing.getProductGroupId(), updated, existingMap, changedImages);

		processDeletedImages(existingMap, changedImages);

		if(!changedImages.isEmpty()){
			ProductGroupImageContextCommand productGroupImageContextCommand = ProductGroupImageContextCommand.of(changedImages);

			decision.addUpdate(productGroupImageContextCommand, ProductDomainEventType.IMAGE,false);
		}
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductGroupImageContext;
	}


	private Map<String, ProductGroupImage> mapByImageUrl(ProductGroupImageContext images) {
		return images.getImages().stream()
			.collect(Collectors.toMap(
				ProductGroupImage::getImageUrl,
				Function.identity(),
				(existing, replacement) -> existing
			));
	}


	private void processUpdatedImages(long productGroupId, ProductGroupImageContextCommand updated, Map<String, ProductGroupImage> existingMap,
									  List<ProductGroupImageCommand> changedImages) {
		for (ProductGroupImageCommand newImage : updated.productGroupImageCommands()) {
			ProductGroupImage existingImage = existingMap.get(newImage.imageUrl());

			if (existingImage != null) {
				if (needsUpdate(existingImage.getOriginUrl(), newImage.originUrl())) {
					ProductGroupImageCommand productGroupImageCommand = newImage.assignProductGroupId(existingImage.getId());
					changedImages.add(productGroupImageCommand);
				}
				existingMap.remove(newImage.imageUrl());
			} else {
				ProductGroupImageCommand assignedProductGroupImageCommand = newImage.assignProductGroupId(productGroupId);
				changedImages.add(assignedProductGroupImageCommand);
			}
		}
	}

	private void processDeletedImages(Map<String, ProductGroupImage> remainingImages, List<ProductGroupImageCommand> changedImages) {
		remainingImages.values().stream()
			.filter(image -> !image.getOriginUrl().isBlank())
			.forEach(image -> {
				ProductGroupImageCommand productGroupImageCommand = ProductGroupImageCommand.of(
					image.getId(),
					image.getProductGroupId(),
					image.getProductImageType(),
					image.getImageUrl(),
					image.getOriginUrl(),
					true
				);

				changedImages.add(productGroupImageCommand);
			});
	}

	private boolean needsUpdate(String existingUrl, String originUrl) {
		return !existingUrl.equals(originUrl);
	}

}
