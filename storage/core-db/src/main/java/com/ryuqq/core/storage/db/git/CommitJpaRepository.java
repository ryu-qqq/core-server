package com.ryuqq.core.storage.db.git;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitJpaRepository extends JpaRepository<CommitEntity, Long> {
}
