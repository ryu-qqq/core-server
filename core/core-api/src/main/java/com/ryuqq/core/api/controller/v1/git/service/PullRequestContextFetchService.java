package com.ryuqq.core.api.controller.v1.git.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.git.mapper.PullRequestSummarySliceMapper;
import com.ryuqq.core.api.controller.v1.git.request.PullRequestFilterDto;
import com.ryuqq.core.api.controller.v1.git.response.PullRequestSummaryResponseDto;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.domain.git.PullRequestFinder;

@Service
public class PullRequestContextFetchService {

	private final PullRequestSummarySliceMapper pullRequestSummarySliceMapper;
	private final PullRequestFinder pullRequestFinder;

	public PullRequestContextFetchService(PullRequestSummarySliceMapper pullRequestSummarySliceMapper,
										  PullRequestFinder pullRequestFinder) {
		this.pullRequestSummarySliceMapper = pullRequestSummarySliceMapper;
		this.pullRequestFinder = pullRequestFinder;
	}

	public Slice<PullRequestSummaryResponseDto> fetchByFilter(PullRequestFilterDto filterDto){
		long totalCount = pullRequestFinder.countByFilter(filterDto.toPullRequestFilter());

		if(totalCount == 0) {
			return pullRequestSummarySliceMapper.toSlice(List.of(), filterDto.getPageSize(), totalCount);
		}

		List<PullRequestSummaryResponseDto> responses = pullRequestFinder.fetchByFilter(filterDto.toPullRequestFilter())
			.stream()
			.map(c -> new PullRequestSummaryResponseDto(
						c.getId(),
						c.getGitPullId(),
						c.getBranchId(),
						c.getGitType(),
						c.getSourceBranch(),
						c.getTargetBranch(),
						c.getTitle(),
						c.getStatus(),
						c.getReviewStatus(),
						c.getCreateAt()
				)
			).toList();

		return pullRequestSummarySliceMapper.toSlice(responses, filterDto.getPageSize(), totalCount);
	}






}
