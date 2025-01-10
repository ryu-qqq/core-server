package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.unit.test.BaseUnitTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BranchJpaPersistenceRepositoryTest extends BaseUnitTest {


	@InjectMocks
	private BranchJpaPersistenceRepository branchJpaPersistenceRepository;

	@Mock
	private BranchJpaRepository branchJpaRepository;


	@DisplayName("BranchCommand 를 BranchEntity 로 저장한다.")
	@Test
	void shouldSaveBranchEntityAndReturnIdWhenBranchCommandProvided() {
		// Given
		BranchCommand branchCommand = mock(BranchCommand.class); // 목 객체 생성
		BranchEntity branchEntity = mock(BranchEntity.class);    // 목 객체 생성
		long expectedId = 1L;

		// 목킹 설정
		when(branchCommand.toEntity()).thenReturn(branchEntity);
		when(branchJpaRepository.save(branchEntity)).thenReturn(branchEntity);
		when(branchEntity.getId()).thenReturn(expectedId);

		// When
		long actualId = branchJpaPersistenceRepository.save(branchCommand);

		// Then
		assertEquals(expectedId, actualId, "저장된 BranchEntity의 ID는 반환된 값과 일치해야 합니다.");
		verify(branchCommand, times(1)).toEntity();
		verify(branchJpaRepository, times(1)).save(branchEntity);
	}


}
