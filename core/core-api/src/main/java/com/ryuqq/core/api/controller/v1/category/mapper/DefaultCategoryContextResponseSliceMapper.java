package com.ryuqq.core.api.controller.v1.category.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.core.api.controller.v1.category.response.DefaultCategoryContextResponseDto;
import com.ryuqq.core.api.payload.Slice;

@Component
public class DefaultCategoryContextResponseSliceMapper extends AbstractGeneralSliceMapper<DefaultCategoryContextResponseDto> {

	@Override
	protected void setCursor(Slice<DefaultCategoryContextResponseDto> slice) {
		if (!slice.isEmpty()) {
			List<DefaultCategoryContextResponseDto> content = slice.getContent();
			DefaultCategoryContextResponseDto t = content.getLast();
			slice.setCursor(t.id());
		}
	}

}
