package com.ryuqq.core.api.controller.v1.git.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.core.api.controller.v1.git.response.PullRequestSummaryResponseDto;
import com.ryuqq.core.api.payload.Slice;

@Component
public class PullRequestSummarySliceMapper extends AbstractGeneralSliceMapper<PullRequestSummaryResponseDto> {

	@Override
	public Slice<PullRequestSummaryResponseDto> toSlice(List<PullRequestSummaryResponseDto> data, int pageSize) {
		return super.toSlice(data, pageSize);
	}

	@Override
	public Slice<PullRequestSummaryResponseDto> toSlice(List<PullRequestSummaryResponseDto> data, int pageSize, long totalElements) {
		if(totalElements == 0) {
			return toEmptySlice(pageSize);
		}
		return super.toSlice(data, pageSize, totalElements);
	}

	@Override
	protected void setCursor(Slice<PullRequestSummaryResponseDto> slice) {
		if (!slice.isEmpty()) {
			List<PullRequestSummaryResponseDto> content = slice.getContent();
			PullRequestSummaryResponseDto t = content.getLast();
			slice.setCursor(t.id());
		}
	}
}
