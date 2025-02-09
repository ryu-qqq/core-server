package com.ryuqq.core.domain.product;



import com.ryuqq.core.unit.test.BaseUnitTest;

class DefaultDefaultDefaultProductGroupImageCheckerTest extends BaseUnitTest {

	// @InjectMocks
	// private ProductGroupImageChecker checker;
	//
	//
	// @ParameterizedTest
	// @MethodSource("provideTestCasesForUpdates")
	// @DisplayName("ProductGroupImageChecker 업데이트 테스트")
	// void testProductGroupImageUpdates(DefaultProductGroupImageContext existing, DefaultProductGroupImageContext updated, int expectedBatchSize) {
	// 	// When
	// 	UpdateDecision decision = checker.checkUpdates(existing, updated);
	//
	// 	// Then
	// 	assertEquals(expectedBatchSize, decision.getBatchUpdates().size(), "배치 업데이트 크기가 일치해야 함");
	// }
	//
	// @Test
	// @DisplayName("모든 이미지가 삭제되었을 때")
	// void testAllImagesDeleted() {
	// 	// Given
	// 	DefaultProductGroupImageContext existing = ProductImageFactoryHelper.createExistingImages(3);
	// 	DefaultProductGroupImageContext updated = new DefaultProductGroupImageContext(List.of());
	//
	// 	// When
	// 	UpdateDecision decision = checker.checkUpdates(existing, updated);
	//
	// 	// Then
	// 	assertEquals(1, decision.getBatchUpdates().size(), "모든 이미지가 삭제되어야 함");
	// 	assertTrue(decision.getBatchUpdates().stream()
	// 		.allMatch(update -> ((DefaultProductGroupImageContext) update.domain())
	// 			.getImages().stream().allMatch(DefaultProductGroupImage::isDeleted)));
	// }
	//
	//
	// private static Stream<Arguments> provideTestCasesForUpdates() {
	// 	return Stream.of(
	// 		Arguments.of(
	// 			ProductImageFactoryHelper.createExistingImages(2),
	// 			ProductImageFactoryHelper.createExistingImages(3),
	// 			1
	// 		),
	// 		Arguments.of(
	// 			ProductImageFactoryHelper.createExistingImages(3),
	// 			ProductImageFactoryHelper.createExistingImages(2),
	// 			1
	// 		),
	// 		Arguments.of(
	// 			ProductImageFactoryHelper.createExistingImages(2),
	// 			new DefaultProductGroupImageContext(List.of(
	// 				DefaultProductGroupImage.create(1L, 1L, ProductImageType.MAIN, "imageUrl-1", "newOriginUrl-1", false),
	// 				DefaultProductGroupImage.create(2L, 1L, ProductImageType.DETAIL, "imageUrl-2", "originUrl-2", false)
	// 			)),
	// 			1
	// 		)
	// 	);
	// }

}
