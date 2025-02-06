package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductGroupImageChecker implements UpdateChecker<ProductGroupImageBundle, ProductGroupImageBundle> {

	@Override
	public UpdateDecision checkUpdates(long productGroupId, ProductGroupImageBundle existing, ProductGroupImageBundle updated) {
		UpdateDecision decision = new UpdateDecision();

		List<ProductGroupImage> changedImages = new ArrayList<>();

		Map<String, ProductGroupImage> existingMap = mapByImageUrl(existing);

		processUpdatedImages(updated, existingMap, changedImages);

		processDeletedImages(existingMap, changedImages);

		if(!changedImages.isEmpty()){
			ProductGroupImageBundle productGroupImageBundle = new ProductGroupImageBundle(changedImages);
			ProductGroupImageBundle assignedProductGroupId = productGroupImageBundle.assignProductGroupId(
				productGroupId);
			decision.addUpdate(assignedProductGroupId, ProductDomainEventType.IMAGE,false);
		}

		return decision;
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductGroupImageBundle;
	}

	/**
	 * 기존 이미지를 URL 기준으로 Map 변환
	 */
	private Map<String, ProductGroupImage> mapByImageUrl(ProductGroupImageBundle images) {
		return images.getImages().stream()
			.collect(Collectors.toMap(
				ProductGroupImage::getImageUrl,
				Function.identity(),
				(existing, replacement) -> existing // 중복 제거 처리
			));
	}

	/**
	 * 새 이미지와 기존 이미지를 비교하여 업데이트 결정
	 */
	private void processUpdatedImages(ProductGroupImageBundle updated, Map<String, ProductGroupImage> existingMap,
									  List<ProductGroupImage> changedImages) {
		for (ProductGroupImage newImage : updated.getImages()) {
			ProductGroupImage existingImage = existingMap.get(newImage.getImageUrl());

			if (existingImage != null) { // 기존 이미지가 존재
				if (existingImage.needsUpdate(newImage.getOriginUrl())) {
					ProductGroupImage updatedImage = createUpdatedImage(existingImage, newImage);
					changedImages.add(updatedImage);

				}
				existingMap.remove(newImage.getImageUrl()); // 처리된 이미지는 Map에서 제거
			} else { // 새 이미지 추가
				changedImages.add(newImage);

			}
		}
	}

	/**
	 * 기존 이미지 중 삭제된 이미지를 처리
	 */
	private void processDeletedImages(Map<String, ProductGroupImage> remainingImages, List<ProductGroupImage> changedImages) {
		remainingImages.values().stream()
			.filter(image -> !image.getOriginUrl().isBlank()) // 삭제 대상 필터링
			.forEach(image -> {
				changedImages.add(image.delete());
			});
	}

	/**
	 * 업데이트된 이미지를 생성
	 */
	private ProductGroupImage createUpdatedImage(ProductGroupImage existing, ProductGroupImage updated) {
		return ProductGroupImage.create(
			existing.getId(),
			existing.getProductGroupId(),
			updated.getProductImageType(),
			updated.getImageUrl(),
			updated.getOriginUrl(),
			false
		);
	}


}
