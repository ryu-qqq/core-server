package com.ryuqq.core.domain.product.dao.group;

import java.util.Optional;

import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
import com.ryuqq.core.enums.OptionType;

public class DefaultCreateProductGroupContextCommand implements ProductGroupContextCommand {

	private final ProductGroupCommand productGroupCommand;
	private final ProductNoticeCommand productNoticeCommand;
	private final ProductDeliveryCommand productDeliveryCommand;
	private final ProductDetailDescriptionCommand productDetailDescriptionCommand;
	private final ProductGroupImageContextCommand productGroupImageContextCommand;
	private final ProductOptionContextCommand productOptionContextCommand;

	DefaultCreateProductGroupContextCommand(ProductGroupCommand productGroupCommand,
												   ProductNoticeCommand productNoticeCommand,
												   ProductDeliveryCommand productDeliveryCommand,
												   ProductDetailDescriptionCommand productDetailDescriptionCommand,
												   ProductGroupImageContextCommand productGroupImageContextCommand,
												   ProductOptionContextCommand productOptionContextCommand) {
		this.productGroupCommand = productGroupCommand;
		this.productNoticeCommand = productNoticeCommand;
		this.productDeliveryCommand = productDeliveryCommand;
		this.productDetailDescriptionCommand = productDetailDescriptionCommand;
		this.productGroupImageContextCommand = productGroupImageContextCommand;
		this.productOptionContextCommand = productOptionContextCommand;
	}

	public static BuilderGroupContext builder() {
		return new BuilderGroupContext();
	}

	@Override
	public ProductGroupCommand getProductGroupCommand() {
		return productGroupCommand;
	}

	@Override
	public ProductNoticeCommand getProductNoticeCommand() {
		return productNoticeCommand;
	}

	@Override
	public ProductDeliveryCommand getProductDeliveryCommand() {
		return productDeliveryCommand;
	}

	@Override
	public ProductDetailDescriptionCommand getProductDetailDescriptionCommand() {
		return productDetailDescriptionCommand;
	}

	@Override
	public ProductGroupImageContextCommand getProductGroupImageContextCommand() {
		return productGroupImageContextCommand;
	}

	@Override
	public ProductOptionContextCommand getProductOptionContextCommand() {
		return productOptionContextCommand;
	}

	public static class BuilderGroupContext implements ProductGroupContextCommandBuilder {
		private OptionType optionType;
		private ProductGroupCommand productGroupCommand;
		private ProductNoticeCommand productNoticeCommand;
		private ProductDeliveryCommand productDeliveryCommand;
		private ProductDetailDescriptionCommand productDetailDescriptionCommand;
		private ProductGroupImageContextCommand productGroupImageContextCommand;
		private ProductOptionContextCommand productOptionContextCommand;

		@Override
		public void withProductGroupId(long productGroupId) {

		}

		@Override
		public void withOptionType(OptionType optionType) {
			this.optionType = optionType;
		}

		@Override
		public void withProductGroupCommand(ProductGroupCommand productGroupCommand) {
			this.productGroupCommand = productGroupCommand;
		}

		@Override
		public void withProductNoticeCommand(ProductNoticeCommand productNoticeCommand) {
			this.productNoticeCommand = productNoticeCommand;
		}

		@Override
		public void withProductDeliveryCommand(ProductDeliveryCommand productDeliveryCommand) {
			this.productDeliveryCommand = productDeliveryCommand;
		}

		@Override
		public void withProductDetailDescriptionCommand(
			ProductDetailDescriptionCommand productDetailDescriptionCommand) {
			this.productDetailDescriptionCommand = productDetailDescriptionCommand;
		}

		@Override
		public void withProductGroupImageContextCommand(
			ProductGroupImageContextCommand productGroupImageContextCommand) {
			this.productGroupImageContextCommand = productGroupImageContextCommand;
		}

		@Override
		public void withProductContextCommand(ProductOptionContextCommand productOptionContextCommand) {
			this.productOptionContextCommand = productOptionContextCommand;
		}

		public DefaultCreateProductGroupContextCommand build() {
			return new DefaultCreateProductGroupContextCommand(
				this.productGroupCommand,
				this.productNoticeCommand,
				this.productDeliveryCommand,
				this.productDetailDescriptionCommand,
				this.productGroupImageContextCommand,
				this.productOptionContextCommand
			);
		}

		@Override
		public Optional<Long> getProductGroupId() {
			return Optional.empty();
		}

		@Override
		public OptionType getOptionType() {
			return optionType;
		}
	}


}
