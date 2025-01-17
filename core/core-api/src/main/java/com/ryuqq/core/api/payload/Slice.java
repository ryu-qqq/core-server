package com.ryuqq.core.api.payload;

import java.util.List;

import com.ryuqq.core.enums.Sort;

public class Slice<T> {
	private final List<T> content;
	private final boolean last;
	private final boolean first;
	private final Sort sort;
	private final int size;
	private final int numberOfElements;
	private final boolean empty;
	private Long cursor;
	private final Long totalElements;

	private Slice(Builder<T> builder) {
		this.content = builder.content;
		this.last = builder.last;
		this.first = builder.first;
		this.sort = builder.sort;
		this.size = builder.size;
		this.numberOfElements = builder.numberOfElements;
		this.empty = builder.empty;
		this.cursor = builder.cursor;
		this.totalElements = builder.totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public boolean isLast() {
		return last;
	}

	public boolean isFirst() {
		return first;
	}

	public Sort getSort() {
		return sort;
	}

	public int getSize() {
		return size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public boolean isEmpty() {
		return empty;
	}

	public Long getCursor() {
		return cursor;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setCursor(Long cursor) {
		this.cursor = cursor;
	}

	public static class Builder<T> {
		private List<T> content;
		private boolean last;
		private boolean first;
		private Sort sort;
		private int size;
		private int numberOfElements;
		private boolean empty;
		private Long cursor;
		private Long totalElements;

		public Builder<T> content(List<T> content) {
			this.content = content;
			return this;
		}

		public Builder<T> last(boolean last) {
			this.last = last;
			return this;
		}

		public Builder<T> first(boolean first) {
			this.first = first;
			return this;
		}

		public Builder<T> sort(Sort sort) {
			this.sort = sort;
			return this;
		}

		public Builder<T> size(int size) {
			this.size = size;
			return this;
		}

		public Builder<T> numberOfElements(int numberOfElements) {
			this.numberOfElements = numberOfElements;
			return this;
		}

		public Builder<T> empty(boolean empty) {
			this.empty = empty;
			return this;
		}

		public Builder<T> cursor(Long cursor) {
			this.cursor = cursor;
			return this;
		}

		public Builder<T> totalElements(Long totalElements) {
			this.totalElements = totalElements;
			return this;
		}

		public Slice<T> build() {
			return new Slice<>(this);
		}
	}
}
