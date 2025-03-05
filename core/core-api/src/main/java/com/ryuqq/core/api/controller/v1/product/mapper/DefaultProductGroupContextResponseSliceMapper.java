package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.core.api.controller.v1.product.response.DefaultProductGroupContextResponseDto;
import com.ryuqq.core.api.payload.Slice;

@Component
public class DefaultProductGroupContextResponseSliceMapper extends AbstractGeneralSliceMapper<DefaultProductGroupContextResponseDto> {

	@Override
	protected void setCursor(Slice<DefaultProductGroupContextResponseDto> slice) {
		if (!slice.isEmpty()) {
			List<DefaultProductGroupContextResponseDto> content = slice.getContent();
			DefaultProductGroupContextResponseDto t = content.getLast();
			slice.setCursor(t.getProductGroup().id());
		}
	}

}
