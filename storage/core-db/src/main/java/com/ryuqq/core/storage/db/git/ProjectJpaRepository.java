package com.ryuqq.core.storage.db.git;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
}
