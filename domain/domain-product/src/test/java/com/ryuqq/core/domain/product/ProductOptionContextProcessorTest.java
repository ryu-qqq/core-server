package com.ryuqq.core.domain.product;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.domain.product.core.Product;
import com.ryuqq.core.domain.product.core.ProductContext;
import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.unit.test.BaseUnitTest;

@DisplayName("ProductOptionContextProcessor 단위 테스트")
class ProductOptionContextProcessorTest extends BaseUnitTest {

	@Nested
	@DisplayName("processNewProductContext() 테스트")
	class ProcessNewProductContextTest {

		@Test
		@DisplayName("새로운 옵션 컨텍스트를 처리하여 적절한 ID를 할당해야 한다.")
		void shouldAssignIdsToNewOptionContext() {
			// Given
			ProductOptionCommand productOptionCommand = createProductOptionCommand(1L, false);
			Map<OptionName, Long> optionGroupIdValueMap = Map.of(OptionName.COLOR, 10L);
			Map<String, Long> optionDetailIdMap = Map.of("Red", 100L);

			// When
			ProductOptionCommand updatedCommand = ProductOptionContextProcessor.processNewProductContext(
				productOptionCommand, optionGroupIdValueMap, optionDetailIdMap
			);

			// Then
			assertNotNull(updatedCommand);
			assertFalse(updatedCommand.optionContextCommands().isEmpty());
			assertEquals(10L, updatedCommand.optionContextCommands().getFirst().optionGroupId());
			assertEquals(100L, updatedCommand.optionContextCommands().getFirst().optionDetailId());
		}

		@Test
		@DisplayName("새로운 옵션 컨텍스트가 존재하지 않을 경우 동일한 데이터를 반환해야 한다.")
		void shouldReturnSameCommandIfNoNewIdsAssigned() {
			// Given
			ProductOptionCommand productOptionCommand = createProductOptionCommand(1L, false);
			Map<OptionName, Long> emptyOptionGroupIdValueMap = Collections.emptyMap();
			Map<String, Long> emptyOptionDetailIdMap = Collections.emptyMap();

			// When
			ProductOptionCommand updatedCommand = ProductOptionContextProcessor.processNewProductContext(
				productOptionCommand, emptyOptionGroupIdValueMap, emptyOptionDetailIdMap
			);

			// Then
			assertNotNull(updatedCommand);
			assertEquals(productOptionCommand, updatedCommand);
		}
	}

	@Nested
	@DisplayName("processDeleteProductContext() 테스트")
	class ProcessDeleteProductContextTest {

		@Test
		@DisplayName("기존 옵션이 삭제되었을 때 삭제된 옵션을 포함한 명령이 추가되어야 한다.")
		void shouldAddDeletedProductOptionCommands() {
			// Given
			Map<String, ProductContext> existingOptionNameValueMap = new HashMap<>();
			existingOptionNameValueMap.put("COLOR-Red", createProductContext(1L, OptionName.COLOR, "Red", false));

			List<ProductOptionCommand> changedProductContext = new ArrayList<>();

			// When
			ProductOptionContextProcessor.processDeleteProductContext(existingOptionNameValueMap, changedProductContext);

			// Then
			assertFalse(changedProductContext.isEmpty());
			assertTrue(changedProductContext.getFirst().deleted());
		}

		@Test
		@DisplayName("삭제할 기존 옵션이 없으면 변경 목록이 비어 있어야 한다.")
		void shouldNotAddDeletedCommandsIfNoExistingOptions() {
			// Given
			Map<String, ProductContext> emptyExistingMap = new HashMap<>();
			List<ProductOptionCommand> changedProductContext = new ArrayList<>();

			// When
			ProductOptionContextProcessor.processDeleteProductContext(emptyExistingMap, changedProductContext);

			// Then
			assertTrue(changedProductContext.isEmpty());
		}
	}


	private ProductOptionCommand createProductOptionCommand(Long id, boolean deleted) {
		ProductCommand productCommand = ProductCommand.of(id, 1L, false, true, 10, BigDecimal.valueOf(1000), deleted);
		List<OptionContextCommand> optionContextCommands = List.of(
			OptionContextCommand.of(1L, 1L, 1L, OptionName.COLOR, "Red")
		);
		return ProductOptionCommand.of(productCommand, deleted, optionContextCommands);
	}

	private ProductContext createProductContext(Long id, OptionName optionName, String optionValue, boolean deleted) {
		return new ProductContext() {
			@Override
			public Product getProduct() {
				return createProduct(id, deleted);
			}

			@Override
			public List<? extends OptionContext> getOptions() {
				return List.of(createOptionContext(id, optionName, optionValue, deleted));
			}

			@Override
			public String getOptionNameValue() {
				return optionName + " " + optionValue;
			}

			@Override
			public boolean isDeleted() {
				return deleted;
			}
		};
	}

	private Product createProduct(Long id, boolean deleted) {
		return new Product() {
			@Override
			public Long getId() {
				return id;
			}

			@Override
			public Long getProductGroupId() {
				return 1L;
			}

			@Override
			public boolean isSoldOut() {
				return false;
			}

			@Override
			public boolean isDisplayed() {
				return true;
			}

			@Override
			public int getQuantity() {
				return 10;
			}

			@Override
			public BigDecimal getAdditionalPrice() {
				return BigDecimal.valueOf(1000);
			}

			@Override
			public boolean isDeleted() {
				return deleted;
			}
		};
	}

	private OptionContext createOptionContext(Long productId, OptionName optionName, String optionValue, boolean deleted) {
		return new OptionContext() {
			@Override
			public Long getProductOptionId() {
				return productId;
			}

			@Override
			public Long getOptionGroupId() {
				return 1L;
			}

			@Override
			public Long getOptionDetailId() {
				return 1L;
			}

			@Override
			public Long getProductId() {
				return productId;
			}

			@Override
			public OptionName getOptionName() {
				return optionName;
			}

			@Override
			public String getOptionValue() {
				return optionValue;
			}

			@Override
			public String getOptionNameValue() {
				return optionName + " " + optionValue;
			}

			@Override
			public boolean isDeleted() {
				return deleted;
			}
		};
	}
}
