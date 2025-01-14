package com.ryuqq.core.storage.db.git;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "LABEL")
public class LabelEntity extends BaseEntity {

	@Column(name = "NAME", nullable = false, length = 255)
	private String name;

	protected LabelEntity() {}

	public LabelEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
