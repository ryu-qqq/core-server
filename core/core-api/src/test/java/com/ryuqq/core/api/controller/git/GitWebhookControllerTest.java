package com.ryuqq.core.api.controller.git;

import static com.ryuqq.core.api.test.RestDocsUtil.requestPreprocessor;
import static com.ryuqq.core.api.test.RestDocsUtil.responsePreprocessor;
import static com.ryuqq.core.api.test.RestDocsUtil.statusMsg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import com.ryuqq.core.api.controller.git.request.GitPushEventRequestDto;
import com.ryuqq.core.api.controller.git.response.GitPushEventResponseDto;
import com.ryuqq.core.api.controller.git.service.GitWebhookHandler;
import com.ryuqq.core.api.data.GitModuleHelper;
import com.ryuqq.core.api.test.RestDocsTest;

import io.restassured.http.ContentType;


class GitWebhookControllerTest extends RestDocsTest {


	@InjectMocks
	private GitWebhookController gitWebhookController;

	@Mock
	private GitWebhookHandler gitWebhookHandler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = mockController(gitWebhookController);
	}

	@DisplayName("POST /git/webhook - Git Webhook 이벤트 처리")
	@Test
	void shouldHandleGitWebhookAndReturnResponse() throws Exception {
		// Given
		GitPushEventRequestDto requestDto = GitModuleHelper.toGitPushEventRequestDto();

		GitPushEventResponseDto responseDto = new GitPushEventResponseDto(1L);
		when(gitWebhookHandler.handle(any(GitPushEventRequestDto.class))).thenReturn(responseDto);

		given()
			.accept(ContentType.JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(requestDto)
			.post("/api/v1/git/webhook")
			.then()
			.apply(document("git-webhook-post", requestPreprocessor(), responsePreprocessor(),
				requestFields(
					fieldWithPath("objectKind").description("Git 이벤트 종류"),
					fieldWithPath("ref").description("Git 참조(ref)"),
					fieldWithPath("project_id").description("프로젝트 ID"),
					fieldWithPath("user_name").description("사용자 이름"),
					subsectionWithPath("commits").description("커밋 리스트"),
					subsectionWithPath("repository").description("레포지토리 정보")
				),
				responseFields(
					beneathPath("data"),
					fieldWithPath("branchId").type(JsonFieldType.NUMBER).description("GIT BRANCH ID")
				),
				responseFields(
					beneathPath("response"),
					statusMsg()
				)
			));
	}



}
