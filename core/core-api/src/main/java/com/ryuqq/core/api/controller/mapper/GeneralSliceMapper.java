package com.ryuqq.core.api.controller.mapper;

import com.ryuqq.core.api.payload.Slice;

import java.util.List;

public interface GeneralSliceMapper<T> {

	Slice<T> toSlice(List<T> data, int pageSize);
	Slice<T> toSlice(List<T> data, int pageSize, long totalElements);

}

