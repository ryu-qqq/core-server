package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.ryuqq.core.domain.product.core.DefaultProductGroupImage;
import com.ryuqq.core.domain.product.core.DefaultProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.core.ProductGroupImageContext;
import com.ryuqq.core.domain.product.core.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.ProductImageType;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductGroupImageChecker 단위 테스트")
class ProductGroupImageCheckerTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupImageChecker productGroupImageChecker;

	@Spy
	private UpdateDecision updateDecision;

	@Nested
	@DisplayName("checkUpdates() 테스트")
	class CheckUpdatesTest {

		@Test
		@DisplayName("새로운 이미지가 추가되었을 때, assignProductGroupId()가 호출되고 업데이트가 추가되어야 한다.")
		void shouldAssignProductGroupIdAndAddUpdateForNewImages() {
			// Given
			ProductGroupImageContext existing = createProductGroupImageContext(
				List.of(createProductGroupImage(1L, "img1.jpg", "origin1.jpg"))
			);

			ProductGroupImageCommand existingImage = spy(ProductGroupImageCommand.of(0L, 1L, ProductImageType.MAIN, "img1.jpg", "origin1.jpg", false));
			ProductGroupImageCommand newImage = spy(ProductGroupImageCommand.of(0L, 1L, ProductImageType.DETAIL, "img2.jpg", "origin2.jpg", false)); // 새로운 이미지 추가됨

			ProductGroupImageContextCommand updated = spy(ProductGroupImageContextCommand.of(List.of(existingImage, newImage)));
			doReturn(newImage).when(newImage).assignProductGroupId(1L);

			// When
			productGroupImageChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(newImage, times(1)).assignProductGroupId(1L); // ✅ 새로운 이미지에 대한 assignProductGroupId() 호출 검증

			ArgumentCaptor<ProductGroupImageContextCommand> captor = ArgumentCaptor.forClass(ProductGroupImageContextCommand.class);
			verify(updateDecision).addUpdate(captor.capture(), eq(ProductDomainEventType.IMAGE), eq(false));

			assertFalse(captor.getValue().productGroupImageCommands().isEmpty());
		}

		@Test
		@DisplayName("기존 이미지의 originUrl이 변경되었을 때 업데이트가 추가되어야 한다.")
		void shouldAddUpdateForUpdatedOriginUrl() {
			// Given
			ProductGroupImageContext existing = createProductGroupImageContext(
				List.of(createProductGroupImage(1L, "img1.jpg", "old-origin1.jpg"))
			);

			ProductGroupImageCommand updatedImage = spy(ProductGroupImageCommand.of(1L, 1L, ProductImageType.MAIN, "img1.jpg", "new-origin1.jpg", false));

			ProductGroupImageContextCommand updated = spy(ProductGroupImageContextCommand.of(List.of(updatedImage)));

			doReturn(updatedImage).when(updatedImage).assignProductGroupId(1L);

			// When
			productGroupImageChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updatedImage, times(1)).assignProductGroupId(1L);
			verify(updateDecision).addUpdate(any(), eq(ProductDomainEventType.IMAGE), eq(false));
		}

		@Test
		@DisplayName("기존 이미지가 삭제되었을 때, 삭제된 이미지에 대한 업데이트가 추가되어야 한다.")
		void shouldAddUpdateForDeletedImages() {
			// Given
			ProductGroupImageContext existing = createProductGroupImageContext(
				List.of(createProductGroupImage(1L, "img1.jpg", "origin1.jpg"))
			);

			ProductGroupImageContextCommand updated = spy(ProductGroupImageContextCommand.of(List.of()));

			// When
			productGroupImageChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updateDecision).addUpdate(any(), eq(ProductDomainEventType.IMAGE), eq(false));
		}

		@Test
		@DisplayName("기존 값과 동일한 경우, UpdateDecision에 업데이트를 추가하지 않아야 한다.")
		void shouldNotAddUpdateIfNoChangesDetected() {
			// Given
			ProductGroupImageContext existing = createProductGroupImageContext(
				List.of(createProductGroupImage(1L, "img1.jpg", "origin1.jpg"))
			);

			ProductGroupImageContextCommand updated = spy(ProductGroupImageContextCommand.of(
				List.of(ProductGroupImageCommand.of(1L, 1L, ProductImageType.MAIN, "img1.jpg", "origin1.jpg", false))
			));

			// When
			productGroupImageChecker.checkUpdates(updateDecision, existing, updated);

			// Then
			verify(updateDecision, never()).addUpdate(any(), any(), anyBoolean());
		}
	}

	@Nested
	@DisplayName("supports() 테스트")
	class SupportsTest {

		@Test
		@DisplayName("DefaultProductGroupImageContext 타입이면 true를 반환해야 한다.")
		void shouldReturnTrueForDefaultProductGroupImageContext() {
			// Given
			ProductGroupImageContext productGroupImageContext = createProductGroupImageContext(List.of());

			// When
			boolean result = productGroupImageChecker.supports(productGroupImageContext);

			// Then
			assertTrue(result);
		}

		@Test
		@DisplayName("DefaultProductGroupImageContext가 아닌 타입이면 false를 반환해야 한다.")
		void shouldReturnFalseForNonDefaultProductGroupImageContext() {
			// Given
			Object otherType = new Object();

			// When
			boolean result = productGroupImageChecker.supports(otherType);

			// Then
			assertFalse(result);
		}

		@Test
		@DisplayName("null 값을 넣으면 false를 반환해야 한다.")
		void shouldReturnFalseForNull() {
			// When
			boolean result = productGroupImageChecker.supports(null);

			// Then
			assertFalse(result);
		}
	}

	private ProductGroupImageContext createProductGroupImageContext(List<DefaultProductGroupImage> images) {
		return new DefaultProductGroupImageContext(1L, images);
	}

	private DefaultProductGroupImage createProductGroupImage(Long id, String imageUrl, String originUrl) {
		return DefaultProductGroupImage.create(id, 1L, ProductImageType.MAIN, imageUrl, originUrl, false);
	}

}
