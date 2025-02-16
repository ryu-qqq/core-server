package com.ryuqq.core.external.openAi;

public record DefaultTranslateResult(
	String originalText,
	String translatedText
) implements TranslateResult {
}
