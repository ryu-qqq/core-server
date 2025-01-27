package com.ryuqq.core.api.controller.v1.git.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.git.ChangedFile;
import com.ryuqq.core.enums.ChangeType;

@Component
public class GitChangedFileCreateAdapter {

	public List<ChangedFile> toChangedFileDomain(List<String> added, List<String> removed, List<String> modified) {
		Map<String, ChangeType> fileChangeMap = new LinkedHashMap<>();

		for (String file : removed) {
			if (isJavaFile(file)) {
				fileChangeMap.put(file, ChangeType.REMOVED);
			}
		}

		for (String file : modified) {
			if (isJavaFile(file) && !fileChangeMap.containsKey(file)) {
				fileChangeMap.put(file, ChangeType.MODIFIED);
			}
		}

		for (String file : added) {
			if (isJavaFile(file) && !fileChangeMap.containsKey(file)) {
				fileChangeMap.put(file, ChangeType.ADDED);
			}
		}

		return fileChangeMap.entrySet().stream()
			.map(entry -> ChangedFile.create(extractClassName(entry.getKey()), entry.getKey(), entry.getValue()))
			.toList();
	}

	private static String extractClassName(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return "";
		}
		return filePath.substring(filePath.lastIndexOf('/') + 1);
	}


	private boolean isJavaFile(String filePath) {
		return filePath != null && filePath.endsWith(".java");
	}


}
