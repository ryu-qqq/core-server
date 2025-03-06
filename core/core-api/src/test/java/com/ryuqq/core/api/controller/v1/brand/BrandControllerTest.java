package com.ryuqq.core.api.controller.v1.brand;

import static com.ryuqq.core.api.test.RestDocsUtil.requestPreprocessor;
import static com.ryuqq.core.api.test.RestDocsUtil.responsePreprocessor;
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

import com.ryuqq.core.api.controller.v1.brand.request.BrandSearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.brand.response.DefaultBrandContextResponseDto;
import com.ryuqq.core.api.controller.v1.brand.service.BrandContextQueryService;
import com.ryuqq.core.api.data.BrandModuleHelper;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.api.payload.SliceUtils;
import com.ryuqq.core.api.test.RestDocsTest;
import com.ryuqq.core.enums.RequesterType;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class BrandControllerTest extends RestDocsTest {


	@Mock
	private BrandContextQueryService brandContextQueryService;

	@InjectMocks
	private BrandController brandController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = mockController(brandController);
	}

	@Test
	@DisplayName("Requester-Type이 DEFAULT일 때 상품 그룹을 정상적으로 조회해야 한다.")
	void shouldFetchBrandContextForDefaultRequesterSuccessfully() {
		// Given
		BrandSearchConditionRequestDto requestDto = new BrandSearchConditionRequestDto(
			0, 10, null, null, null, null,
			null, null, null
		);

		List<DefaultBrandContextResponseDto> brandContexts = BrandModuleHelper.createBrandContextResponseDtos();

		Slice<DefaultBrandContextResponseDto> responseDto = SliceUtils.toSlice(brandContexts, 10, 5);
		responseDto.setCursor(4860L);

		when(brandContextQueryService.fetchByConditionForRequester(
			any(BrandSearchConditionRequestDto.class), eq(RequesterType.DEFAULT)))
			.thenReturn(responseDto);

		// When & Then
		given()
			.param("page", String.valueOf(requestDto.page()))
			.param("size", String.valueOf(requestDto.size()))
			.header("Requester-Type", "DEFAULT")
			.when()
			.get("/api/v1/brand")
			.then()
			.statusCode(HttpStatus.OK.value())
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.log().all()
			.apply(document("brand-get-default", requestPreprocessor(), responsePreprocessor(),
				queryParameters(
					parameterWithName("page").description("페이지 번호 (기본값: 0)").optional(),
					parameterWithName("size").description("페이지 크기 (기본값: 20)").optional(),
					parameterWithName("brandIds").description("브랜드 ID 목록").optional(),
					parameterWithName("brandSortField").optional().description("브랜드 정렬 기준 (ID)").optional(),
					parameterWithName("sort").description("정렬 방식 (ASC/DESC)").optional(),
					parameterWithName("cursorId").optional().description("페이징을 위한 커서 ID").optional(),
					parameterWithName("brandSearchField").optional().description("브랜드 검색 필드 (ID, PRODUCT_GROUP_NAME)").optional(),
					parameterWithName("searchWord").optional().description("검색 키워드").optional(),
					parameterWithName("mainDisplayNameType").optional().description("브랜드명 검색 기준 한글 or 영어 (KR, US)").optional()
				),
				requestHeaders(
					headerWithName("Requester-Type").optional().description("요청자 타입 (DEFAULT, ADMIN, WEB)")
				),
				responseFields(
					fieldWithPath("data.content[].id").description("브랜드 ID"),
					fieldWithPath("data.content[].brandName").description("브랜드 명"),
					fieldWithPath("data.content[].brandNameKr").description("브랜드 한글 명"),
					fieldWithPath("data.content[].displayed").description("전시 여부"),

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
