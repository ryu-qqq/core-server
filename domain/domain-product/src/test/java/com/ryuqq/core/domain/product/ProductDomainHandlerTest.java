package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
import com.ryuqq.core.unit.test.BaseUnitTest;

class ProductDomainHandlerTest extends BaseUnitTest {

	@InjectMocks
	private ProductDomainHandler productDomainHandler;

	@Mock
	private ProductRegister productRegister;

	@Mock
	private ProductOptionRegister productOptionRegister;

	@Mock
	private OptionDomainHandler optionDomainHandler;

	@Mock
	private ProductOptionContextCommand mockProductOptionContextCommand;

	@Mock
	private ProductOptionCommand mockProductOptionCommand;

	@Mock
	private ProductCommand mockProductCommand;

	@Mock
	private List<OptionContextCommand> mockOptionContextCommands;


	@Test
	@DisplayName("handle()은 ProductGroupId가 할당된 Product를 등록하고 옵션 도메인을 처리해야 한다.")
	void shouldRegisterProductsAndHandleOptions() {
		// Given
		long productGroupId = 100L;
		when(mockProductOptionContextCommand.assignProductGroupId(productGroupId)).thenReturn(mockProductOptionContextCommand);
		when(mockProductOptionCommand.productCommand()).thenReturn(mockProductCommand);
		when(mockOptionContextCommands.isEmpty()).thenReturn(false);


		doReturn(List.of(mockProductOptionCommand))
			.when(mockProductOptionContextCommand)
			.productCommands();

		doReturn(mockOptionContextCommands)
			.when(mockProductOptionCommand)
			.optionContextCommands();

		// When
		assertDoesNotThrow(() -> productDomainHandler.handle(productGroupId, mockProductOptionContextCommand));

		// Then
		verify(productRegister).register(mockProductCommand);
		verify(optionDomainHandler).handle(any(Map.class));
	}

	@Test
	@DisplayName("handle()은 삭제 대상 Product를 삭제하고 Product를 업데이트해야 한다.")
	void shouldDeleteProductsAndUpdateExistingOnes() {
		// Given
		doReturn(List.of(mockProductOptionCommand))
			.when(mockProductOptionContextCommand)
			.productCommands();

		when(mockProductOptionCommand.deleted()).thenReturn(true);
		when(mockProductOptionCommand.productCommand()).thenReturn(mockProductCommand);
		when(mockProductCommand.id()).thenReturn(200L);

		// When
		assertDoesNotThrow(() -> productDomainHandler.handle(mockProductOptionContextCommand));

		// Then
		verify(productRegister).update(anyList());
		verify(productOptionRegister).delete(eq(List.of(200L)));
	}

	@Test
	@DisplayName("handle()은 삭제할 Product가 없으면 delete를 호출하지 않아야 한다.")
	void shouldNotCallDeleteIfNoProductsToRemove() {
		// Given
		doReturn(List.of(mockProductOptionCommand))
			.when(mockProductOptionContextCommand)
			.productCommands();

		when(mockProductOptionCommand.deleted()).thenReturn(false);

		// When
		assertDoesNotThrow(() -> productDomainHandler.handle(mockProductOptionContextCommand));

		// Then
		verify(productRegister).update(anyList());
		verify(productOptionRegister, never()).delete(anyList());
	}

}
