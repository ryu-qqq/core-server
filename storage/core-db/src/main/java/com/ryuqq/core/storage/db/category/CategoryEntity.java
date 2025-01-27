package com.ryuqq.core.storage.db.category;

import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "CATEGORY")
@Entity
public class CategoryEntity extends BaseEntity {

	@Column(name = "CATEGORY_NAME", length = 50, nullable = false)
	private String categoryName;

	@Column(name = "DEPTH", nullable = false)
	private int depth;

	@Column(name = "PARENT_CATEGORY_ID", nullable = false)
	private long parentCategoryId;

	@Column(name = "DISPLAYED", length = 1)
	private boolean displayed;

	@Column(name = "TARGET_GROUP", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private TargetGroup targetGroup;

	@Column(name = "CATEGORY_TYPE", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private CategoryType categoryType;

	@Column(name = "PATH", length = 50, nullable = false)
	private String path;

	protected CategoryEntity() {}

	public CategoryEntity(String categoryName, int depth, long parentCategoryId, boolean displayed,
						  TargetGroup targetGroup,
						  CategoryType categoryType, String path) {
		this.categoryName = categoryName;
		this.depth = depth;
		this.parentCategoryId = parentCategoryId;
		this.displayed = displayed;
		this.targetGroup = targetGroup;
		this.categoryType = categoryType;
		this.path = path;
	}


}
