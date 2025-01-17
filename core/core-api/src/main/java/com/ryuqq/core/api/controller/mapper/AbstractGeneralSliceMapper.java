package com.ryuqq.core.api.controller.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.api.payload.SliceUtils;

@Component
public abstract class AbstractGeneralSliceMapper <T> implements GeneralSliceMapper<T> {

	@Override
	public Slice<T> toSlice(List<T> data, int pageSize) {
		Slice<T> slice = SliceUtils.toSlice(data, pageSize);
		setCursor(slice);
		return slice;
	}

	@Override
	public Slice<T> toSlice(List<T> data, int pageSize, long totalElements) {
		Slice<T> slice = SliceUtils.toSlice(data, pageSize, totalElements);
		setCursor(slice);
		return slice;
	}

	protected Slice<T> toEmptySlice(int pageSize) {
		return SliceUtils.emptySlice(pageSize);
	}

	protected abstract void setCursor(Slice<T> slice);

}
