package com.ryuqq.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ryuqq.core.domain.git.Branch;
import com.ryuqq.core.domain.git.BranchRegister;
import com.ryuqq.core.storage.db.git.BranchCommand;
import com.ryuqq.core.storage.db.git.BranchPersistenceRepository;
import com.ryuqq.core.unit.test.BaseUnitTest;

class BranchRegisterTest extends BaseUnitTest {

	@InjectMocks
	private BranchRegister branchRegister;

	@Mock
	private BranchPersistenceRepository branchPersistenceRepository;


	@DisplayName("Branch 처리 시 Branch을 저장한다")
	@Test
	void shouldRegisterBranchWhenBranchProvided(){

		Branch branch = mock(Branch.class);
		BranchCommand branchCommand = mock(BranchCommand.class);
		long expectedId = 1L;

		when(branchPersistenceRepository.save(branchCommand)).thenReturn(expectedId);
		when(branch.toCommand()).thenReturn(branchCommand);

		long actualId = branchRegister.register(branch);

		assertEquals(expectedId, actualId, "Branch ID는 기대값과 같아야 합니다");
		verify(branchPersistenceRepository, times(1)).save(branchCommand);

	}

}
