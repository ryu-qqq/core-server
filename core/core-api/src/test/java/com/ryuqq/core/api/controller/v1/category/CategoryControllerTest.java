package com.ryuqq.core.api.controller.v1.category;

import static com.ryuqq.core.api.test.RestDocsUtil.requestPreprocessor;
import static com.ryuqq.core.api.test.RestDocsUtil.responsePreprocessor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;

import com.ryuqq.core.api.controller.v1.category.request.CategorySearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.category.response.DefaultCategoryContextResponseDto;
import com.ryuqq.core.api.controller.v1.category.service.CategoryContextQueryService;
import com.ryuqq.core.api.data.CategoryModuleHelper;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.api.payload.SliceUtils;
import com.ryuqq.core.api.test.RestDocsTest;
import com.ryuqq.core.enums.RequesterType;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class CategoryControllerTest extends RestDocsTest {


	@Mock
	private CategoryContextQueryService categoryContextQueryService;

	@InjectMocks
	private CategoryController categoryController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = mockController(categoryController);
	}


	@Test
	@DisplayName("Requester-Type이 DEFAULT일 때 상품 그룹을 정상적으로 조회해야 한다.")
	void shouldFetchBrandContextForDefaultRequesterSuccessfully() {
		// Given
		CategorySearchConditionRequestDto requestDto = new CategorySearchConditionRequestDto(
			0, 10, null, null, null, null, null, null,
			null, null
		);

		List<DefaultCategoryContextResponseDto> categoryContexts = CategoryModuleHelper.createCategoryContextResponseDtos();

		Slice<DefaultCategoryContextResponseDto> responseDto = SliceUtils.toSlice(categoryContexts, 10, 4);
		responseDto.setCursor(1549L);

		when(categoryContextQueryService.fetchByConditionForRequester(
			any(CategorySearchConditionRequestDto.class), eq(RequesterType.DEFAULT)))
			.thenReturn(responseDto);

		// When & Then
		given()
			.param("page", String.valueOf(requestDto.page()))
			.param("size", String.valueOf(requestDto.size()))
			.header("Requester-Type", "DEFAULT")
			.when()
			.get("/api/v1/category")
			.then()
			.statusCode(HttpStatus.OK.value())
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.log().all()
			.apply(document("category-get-default", requestPreprocessor(), responsePreprocessor(),
				queryParameters(
					parameterWithName("page").description("페이지 번호 (기본값: 0)").optional(),
					parameterWithName("size").description("페이지 크기 (기본값: 20)").optional(),
					parameterWithName("targetGroup").description("카테고리 타겟 (MALE, FEMALE, KIDS, LIFE)").optional(),
					parameterWithName("categoryType").description("카테고리 타입 (NONE, CLOTHING, SHOES, BAG, ACC)").optional(),
					parameterWithName("categoryIds").description("카테고리 ID 목록").optional(),
					parameterWithName("categorySortField").optional().description("카테고리 정렬 기준 (ID)").optional(),
					parameterWithName("sort").description("정렬 방식 (ASC/DESC)").optional(),
					parameterWithName("cursorId").optional().description("페이징을 위한 커서 ID").optional(),
					parameterWithName("categorySearchField").optional().description("카테고리 검색 필드 (ID, PRODUCT_GROUP_NAME)").optional(),
					parameterWithName("searchWord").optional().description("검색 키워드").optional(),
					parameterWithName("mainDisplayNameType").optional().description("카테고리명 검색 기준 한글 or 영어 (KR, US)").optional()
				),
				requestHeaders(
					headerWithName("Requester-Type").optional().description("요청자 타입 (DEFAULT, ADMIN, WEB)")
				),
				responseFields(
					fieldWithPath("data.content[].id").description("카테고리 ID"),
					fieldWithPath("data.content[].categoryName").description("카테고리 명"),
					fieldWithPath("data.content[].depth").description("카테고리 댑스"),
					fieldWithPath("data.content[].parentCategoryId").description("부모 카테고리 ID"),
					fieldWithPath("data.content[].displayed").description("전시 여부"),
					fieldWithPath("data.content[].targetGroup").description("카테고리 타겟 (MALE, FEMALE, KIDS, LIFE)"),
					fieldWithPath("data.content[].categoryType").description("카테고리 타입 (NONE, CLOTHING, SHOES, BAG, ACC)"),
					fieldWithPath("data.content[].path").description("카테고리 패스 "),


					fieldWithPath("data.last").description("마지막 페이지 여부 (`true`: 마지막 페이지)"),
					fieldWithPath("data.first").description("첫 번째 페이지 여부 (`true`: 첫 번째 페이지)"),
					fieldWithPath("data.sort").description("정렬 기준 (`ASC`, `DESC`)"),
					fieldWithPath("data.size").description("한 페이지당 데이터 개수"),
					fieldWithPath("data.numberOfElements").description("현재 페이지의 데이터 개수"),
					fieldWithPath("data.empty").description("현재 페이지가 비어있는지 여부 (`true`: 데이터 없음)"),
					fieldWithPath("data.cursor").description("현재 페이지의 커서 ID"),
					fieldWithPath("data.totalElements").description("전체 데이터 개수"),

					fieldWithPath("response.status").description("응답 상태 코드"),
					fieldWithPath("response.message").description("응답 메시지")
				)
			));
	}


}
