package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;

import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductImageType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupImageCheckerTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupImageChecker checker;


	@ParameterizedTest
	@MethodSource("provideTestCasesForUpdates")
	@DisplayName("ProductGroupImageChecker 업데이트 테스트")
	void testProductGroupImageUpdates(ProductGroupImageBundle existing,ProductGroupImageBundle updated, int expectedBatchSize) {
		// When
		UpdateDecision decision = checker.checkUpdates(existing, updated);

		// Then
		assertEquals(expectedBatchSize, decision.getBatchUpdates().size(), "배치 업데이트 크기가 일치해야 함");
	}

	@Test
	@DisplayName("모든 이미지가 삭제되었을 때")
	void testAllImagesDeleted() {
		// Given
		ProductGroupImageBundle existing = ProductImageFactoryHelper.createExistingImages(3);
		ProductGroupImageBundle updated = new ProductGroupImageBundle(List.of());

		// When
		UpdateDecision decision = checker.checkUpdates(existing, updated);

		// Then
		assertEquals(1, decision.getBatchUpdates().size(), "모든 이미지가 삭제되어야 함");
		assertTrue(decision.getBatchUpdates().stream()
			.allMatch(update -> ((ProductGroupImageBundle) update.domain())
				.getImages().stream().allMatch(ProductGroupImage::isDeleted)));
	}


	private static Stream<Arguments> provideTestCasesForUpdates() {
		return Stream.of(
			Arguments.of(
				ProductImageFactoryHelper.createExistingImages(2),
				ProductImageFactoryHelper.createExistingImages(3),
				1
			),
			Arguments.of(
				ProductImageFactoryHelper.createExistingImages(3),
				ProductImageFactoryHelper.createExistingImages(2),
				1
			),
			Arguments.of(
				ProductImageFactoryHelper.createExistingImages(2),
				new ProductGroupImageBundle(List.of(
					ProductGroupImage.create(1L, 1L, ProductImageType.MAIN, "imageUrl-1", "newOriginUrl-1", false),
					ProductGroupImage.create(2L, 1L, ProductImageType.DETAIL, "imageUrl-2", "originUrl-2", false)
				)),
				1
			)
		);
	}

}
