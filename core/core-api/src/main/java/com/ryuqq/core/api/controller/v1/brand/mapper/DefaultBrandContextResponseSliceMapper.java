package com.ryuqq.core.api.controller.v1.brand.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.core.api.controller.v1.brand.response.DefaultBrandContextResponseDto;
import com.ryuqq.core.api.payload.Slice;

@Component
public class DefaultBrandContextResponseSliceMapper extends AbstractGeneralSliceMapper<DefaultBrandContextResponseDto> {

	@Override
	protected void setCursor(Slice<DefaultBrandContextResponseDto> slice) {
		if (!slice.isEmpty()) {
			List<DefaultBrandContextResponseDto> content = slice.getContent();
			DefaultBrandContextResponseDto t = content.getLast();
			slice.setCursor(t.id());
		}
	}

}
