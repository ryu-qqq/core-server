package com.ryuqq.core.storage.db.category;

import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

public class CategoryDto {

    private final long id;

    private final String categoryName;

    private final int depth;

    private final long parentCategoryId;

    private final boolean displayed;

    private final TargetGroup targetGroup;

    private final CategoryType categoryType;

    private final String path;


    @QueryProjection
    public CategoryDto(long id, String categoryName, int depth, long parentCategoryId, boolean displayed, TargetGroup targetGroup, CategoryType categoryType, String path) {
        this.id = id;
        this.categoryName = categoryName;
        this.depth = depth;
        this.parentCategoryId = parentCategoryId;
        this.displayed = displayed;
        this.targetGroup = targetGroup;
        this.categoryType = categoryType;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getDepth() {
        return depth;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public TargetGroup getTargetGroup() {
        return targetGroup;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public String getPath() {
        return path;
    }
}
