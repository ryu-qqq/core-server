package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.ProductDelivery;
import com.ryuqq.core.domain.product.core.ProductDetailDescription;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductNotice;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupContextCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductGroupContextUpdaterTest extends BaseUnitTest {

	@InjectMocks
	private ProductGroupContextUpdater productGroupContextUpdater;

	@Mock
	private ProductGroupContextFinder productGroupContextFinder;

	@Mock
	private UpdateCheckerExecutor updateCheckerExecutor;

	@Mock
	private UpdateDecisionExecutor updateDecisionExecutor;

	@Mock
	private ProductGroupContext mockProductGroupContext;

	@Mock
	private ProductGroupContextCommand mockProductGroupContextCommand;


	@Test
	@DisplayName("updateProductGroupContext()는 UpdateDecision을 반환해야 한다.")
	void shouldReturnUpdateDecision() {
		// Given
		long productGroupId = 1L;
		when(productGroupContextFinder.fetchById(productGroupId)).thenReturn(mockProductGroupContext);
		when(mockProductGroupContextCommand.getProductGroupCommand()).thenReturn(mock(ProductGroupCommand.class));
		when(mockProductGroupContextCommand.getProductNoticeCommand()).thenReturn(mock(ProductNoticeCommand.class));
		when(mockProductGroupContextCommand.getProductDeliveryCommand()).thenReturn(mock(ProductDeliveryCommand.class));
		when(mockProductGroupContextCommand.getProductDetailDescriptionCommand()).thenReturn(mock(
			ProductDetailDescriptionCommand.class));
		when(mockProductGroupContextCommand.getProductGroupImageCommandContextCommand()).thenReturn(mock(
			ProductGroupImageContextCommand.class));
		when(mockProductGroupContextCommand.getProductCommandContextCommand()).thenReturn(mock(
			ProductOptionContextCommand.class));

		doNothing().when(updateCheckerExecutor).executeChecker(any(), any(), any());
		doNothing().when(updateDecisionExecutor).execute(any());

		// When
		UpdateDecision decision = productGroupContextUpdater.updateProductGroupContext(productGroupId, mockProductGroupContextCommand);

		// Then
		assertNotNull(decision);
		verify(updateDecisionExecutor).execute(decision);
	}

	@Test
	@DisplayName("compareAndUpdateFields()는 모든 필드를 비교해야 한다.")
	void shouldCompareAndUpdateAllFields() {
		// Given
		long productGroupId = 1L;
		when(productGroupContextFinder.fetchById(productGroupId)).thenReturn(mockProductGroupContext);

		when(mockProductGroupContext.getProductGroup()).thenReturn(mock(ProductGroup.class));
		when(mockProductGroupContext.getProductNotice()).thenReturn(mock(ProductNotice.class));
		when(mockProductGroupContext.getProductDelivery()).thenReturn(mock(ProductDelivery.class));
		when(mockProductGroupContext.getProductDetailDescription()).thenReturn(mock(ProductDetailDescription.class));
		when(mockProductGroupContext.getProductGroupImageContext()).thenReturn(mock(ProductGroupImageContext.class));
		when(mockProductGroupContext.getProductOptionContext()).thenReturn(mock(ProductOptionContext.class));

		when(mockProductGroupContextCommand.getProductGroupCommand()).thenReturn(mock(ProductGroupCommand.class));
		when(mockProductGroupContextCommand.getProductNoticeCommand()).thenReturn(mock(ProductNoticeCommand.class));
		when(mockProductGroupContextCommand.getProductDeliveryCommand()).thenReturn(mock(ProductDeliveryCommand.class));
		when(mockProductGroupContextCommand.getProductDetailDescriptionCommand()).thenReturn(mock(ProductDetailDescriptionCommand.class));
		when(mockProductGroupContextCommand.getProductGroupImageCommandContextCommand()).thenReturn(mock(ProductGroupImageContextCommand.class));
		when(mockProductGroupContextCommand.getProductCommandContextCommand()).thenReturn(mock(ProductOptionContextCommand.class));

		// When
		productGroupContextUpdater.updateProductGroupContext(1L, mockProductGroupContextCommand);

		// Then
		verify(updateCheckerExecutor, times(6)).executeChecker(any(), any(), any());
	}

	@Test
	@DisplayName("fetchById()에서 상품 조회 실패 시 DomainException 이 발생해야 한다.")
	void shouldThrowDomainExceptionIfProductGroupFetchFailOnUpdateProductGroupContext() {
		// Given
		long productGroupId = 1L;
		doThrow(new DomainException(ErrorType.NOT_FOUND_ERROR, "cannot find product group"))
			.when(productGroupContextFinder).fetchById(productGroupId);

		// When & Then
		DomainException exception = assertThrows(DomainException.class, () -> productGroupContextUpdater.updateProductGroupContext(productGroupId, mockProductGroupContextCommand));
		assertTrue(exception.getMessage().contains("cannot find product group"));

		verify(updateDecisionExecutor, never()).execute(any());
	}


}
