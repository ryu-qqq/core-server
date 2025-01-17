package com.ryuqq.core.api.controller.mapper;

import java.util.List;

import com.ryuqq.core.api.payload.Slice;

public interface GeneralSliceMapper<T> {

	Slice<T> toSlice(List<T> data, int pageSize);
	Slice<T> toSlice(List<T> data, int pageSize, long totalElements);

}
