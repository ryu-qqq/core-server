package com.ryuqq.core.api.controller.v1.git.mapper;

import com.ryuqq.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.core.api.controller.v1.git.response.ChangedFileResponseDto;
import com.ryuqq.core.api.payload.Slice;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ChangedFileSliceMapper extends AbstractGeneralSliceMapper<ChangedFileResponseDto> {

    @Override
    public Slice<ChangedFileResponseDto> toSlice(List<ChangedFileResponseDto> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<ChangedFileResponseDto> toSlice(List<ChangedFileResponseDto> data, int pageSize, long totalElements) {
		if(totalElements == 0) {
			return toEmptySlice(pageSize);
		}
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ChangedFileResponseDto> slice) {
        if (!slice.isEmpty()) {
            List<ChangedFileResponseDto> content = slice.getContent();
			ChangedFileResponseDto t = content.getLast();
            slice.setCursor(t.changedFileId());
        }
    }

}
