package com.ryuqq.core.api.controller.v1.git.service;

import com.ryuqq.core.api.controller.v1.git.mapper.ChangedFileResponseMapper;
import com.ryuqq.core.api.controller.v1.git.mapper.ChangedFileSliceMapper;
import com.ryuqq.core.api.controller.v1.git.request.ChangedFileRequestFilterDto;
import com.ryuqq.core.api.controller.v1.git.response.ChangedFileResponseDto;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.domain.git.ChangedFileFinder;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChangedFileFetchService {

	private final ChangedFileSliceMapper changedFileSliceMapper;
	private final ChangedFileResponseMapper changedFileResponseMapper;
	private final ChangedFileFinder changedFileFinder;

	public ChangedFileFetchService(ChangedFileSliceMapper changedFileSliceMapper,
								   ChangedFileResponseMapper changedFileResponseMapper, ChangedFileFinder changedFileFinder) {
		this.changedFileSliceMapper = changedFileSliceMapper;
		this.changedFileResponseMapper = changedFileResponseMapper;
		this.changedFileFinder = changedFileFinder;
	}

	public Slice<ChangedFileResponseDto> fetchChangedFiles(ChangedFileRequestFilterDto requestFilterDto) {
		long totalCount = changedFileFinder.countByFilter(requestFilterDto.toReviewExecutionRequestFilter());

		if(totalCount == 0) {
			return changedFileSliceMapper.toSlice(List.of(), requestFilterDto.getPageSize(), totalCount);
		}

		List<ChangedFileResponseDto> responses = changedFileFinder.fetchByFilter(requestFilterDto.toReviewExecutionRequestFilter())
			.stream()
			.map(c -> changedFileResponseMapper.toResponseDto(requestFilterDto.gitType(), c))
			.toList();

		return changedFileSliceMapper.toSlice(responses, requestFilterDto.getPageSize(), totalCount);
	}

}
