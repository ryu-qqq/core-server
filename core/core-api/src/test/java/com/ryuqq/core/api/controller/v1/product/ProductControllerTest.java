package com.ryuqq.core.api.controller.v1.product;

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

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupSearchConditionRequestDto;
import com.ryuqq.core.api.controller.v1.product.response.DefaultProductGroupContextResponseDto;
import com.ryuqq.core.api.controller.v1.product.service.ProductGroupContextQueryFacade;
import com.ryuqq.core.api.data.ProductGroupModuleHelper;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.api.payload.SliceUtils;
import com.ryuqq.core.api.test.RestDocsTest;
import com.ryuqq.core.enums.RequesterType;
import com.ryuqq.core.enums.Sort;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class ProductControllerTest extends RestDocsTest {

	@Mock
	private ProductGroupContextQueryFacade productGroupContextQueryFacade;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = mockController(productController);
	}

	@Test
	@DisplayName("Requester-Type이 DEFAULT일 때 상품 그룹을 정상적으로 조회해야 한다.")
	void shouldFetchProductGroupContextForDefaultRequesterSuccessfully() {
		// Given
		ProductGroupSearchConditionRequestDto requestDto = new ProductGroupSearchConditionRequestDto(
			0, 10, null, null, null, null,
			null, true, false, null, null, null, null, Sort.DESC,
			null, null, null, null,
			null, null
		);

		List<DefaultProductGroupContextResponseDto> defaultProductGroupContexts = List.of(
			ProductGroupModuleHelper.createDefaultProductGroupContextResponseDto(ProductGroupModuleHelper.createSingleProductGroup()),
			ProductGroupModuleHelper.createDefaultProductGroupContextResponseDto(ProductGroupModuleHelper.createOptionOneProductGroup()),
			ProductGroupModuleHelper.createDefaultProductGroupContextResponseDto(ProductGroupModuleHelper.createOptionTwoProductGroup())
		);

		Slice<DefaultProductGroupContextResponseDto> responseDto = SliceUtils.toSlice(defaultProductGroupContexts, 10, 3);
		responseDto.setCursor(3L);

		when(productGroupContextQueryFacade.fetchByConditionForRequester(
			any(ProductGroupSearchConditionRequestDto.class), eq(RequesterType.DEFAULT)))
			.thenReturn(responseDto);

		// When & Then
		given()
			.param("page", String.valueOf(requestDto.page()))
			.param("size", String.valueOf(requestDto.size()))
			.header("Requester-Type", "DEFAULT")
			.when()
			.get("/api/v1/product/group")
			.then()
			.statusCode(HttpStatus.OK.value())
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.log().all()
			.apply(document("product-group-get-default", requestPreprocessor(), responsePreprocessor(),
				queryParameters(
					parameterWithName("page").description("페이지 번호 (기본값: 0)").optional(),
					parameterWithName("size").description("페이지 크기 (기본값: 20)").optional(),
					parameterWithName("sellerIds").description("판매자 ID 목록").optional(),
					parameterWithName("productGroupIds").description("상품 그룹 ID 목록").optional(),
					parameterWithName("categoryIds").description("카테고리 ID 목록").optional(),
					parameterWithName("brandIds").description("브랜드 ID 목록").optional(),
					parameterWithName("minSalePrice").optional().description("최소 판매 가격").optional(),
					parameterWithName("maxSalePrice").optional().description("최대 판매 가격").optional(),
					parameterWithName("minDiscountRate").optional().description("최소 할인율").optional(),
					parameterWithName("maxDiscountRate").optional().description("최대 할인율").optional(),
					parameterWithName("sort").description("정렬 방식 (ASC/DESC)").optional(),
					parameterWithName("productSortField").optional().description("상품 정렬 기준 (ID, PRICE)").optional(),
					parameterWithName("cursorId").optional().description("페이징을 위한 커서 ID").optional(),
					parameterWithName("productSearchField").optional().description("상품 검색 필드 (ID, PRODUCT_GROUP_NAME)").optional(),
					parameterWithName("searchWord").optional().description("검색 키워드").optional(),
					parameterWithName("createdAtFrom").optional().description("생성일 시작 (YYYY-MM-DD HH:mm:ss)").optional(),
					parameterWithName("createdAtTo").optional().description("생성일 종료 (YYYY-MM-DD HH:mm:ss)").optional()
				),
				requestHeaders(
					headerWithName("Requester-Type").optional().description("요청자 타입 (DEFAULT, ADMIN, WEB)")
				),
				responseFields(
					fieldWithPath("data.content[].productGroup.id").description("상품 그룹 ID"),
					fieldWithPath("data.content[].productGroup.sellerId").description("판매자 ID"),
					fieldWithPath("data.content[].productGroup.sellerName").description("판매자 이름"),
					fieldWithPath("data.content[].productGroup.categoryId").description("카테고리 ID"),
					fieldWithPath("data.content[].productGroup.brandId").description("브랜드 ID"),
					fieldWithPath("data.content[].productGroup.productGroupName").description("상품 그룹명"),
					fieldWithPath("data.content[].productGroup.styleCode").description("스타일 코드"),
					fieldWithPath("data.content[].productGroup.productCondition").description("상품 상태 (예: NEW, USED)"),
					fieldWithPath("data.content[].productGroup.managementType").description("상품 관리 타입"),
					fieldWithPath("data.content[].productGroup.optionType").description("옵션 타입"),

					fieldWithPath("data.content[].productGroup.price.regularPrice").description("정가"),
					fieldWithPath("data.content[].productGroup.price.currentPrice").description("현재 판매 가격"),
					fieldWithPath("data.content[].productGroup.price.salePrice").description("할인가"),
					fieldWithPath("data.content[].productGroup.price.directDiscountPrice").description("즉시 할인 금액"),
					fieldWithPath("data.content[].productGroup.price.directDiscountRate").description("즉시 할인율"),
					fieldWithPath("data.content[].productGroup.price.discountRate").description("할인율"),

					fieldWithPath("data.content[].productGroup.soldOut").description("품절 여부"),
					fieldWithPath("data.content[].productGroup.displayed").description("노출 여부"),
					fieldWithPath("data.content[].productGroup.productStatus").description("상품 상태 (예: WAITING, ACTIVE)"),
					fieldWithPath("data.content[].productGroup.keyword").description("상품 키워드"),
					fieldWithPath("data.content[].productGroup.createAt").description("상품 등록일 (yyyy-MM-dd HH:mm:ss)"),
					fieldWithPath("data.content[].productGroup.updateAt").description("상품 수정일 (yyyy-MM-dd HH:mm:ss)"),

					fieldWithPath("data.content[].productNotice.material").description("소재 정보"),
					fieldWithPath("data.content[].productNotice.color").description("색상 정보"),
					fieldWithPath("data.content[].productNotice.size").description("사이즈 정보"),
					fieldWithPath("data.content[].productNotice.maker").description("제조사 정보"),
					fieldWithPath("data.content[].productNotice.origin").description("원산지"),
					fieldWithPath("data.content[].productNotice.washingMethod").description("세탁 방법"),
					fieldWithPath("data.content[].productNotice.yearMonth").description("제조 연월"),
					fieldWithPath("data.content[].productNotice.assuranceStandard").description("품질 보증 기준"),
					fieldWithPath("data.content[].productNotice.asPhone").description("A/S 연락처"),

					fieldWithPath("data.content[].productDelivery.deliveryArea").description("배송 지역"),
					fieldWithPath("data.content[].productDelivery.deliveryFee").description("배송비"),
					fieldWithPath("data.content[].productDelivery.deliveryPeriodAverage").description("평균 배송 소요 기간"),
					fieldWithPath("data.content[].productDelivery.returnMethodDomestic").description("국내 반품 방법"),
					fieldWithPath("data.content[].productDelivery.returnCourierDomestic").description("국내 반품 택배사 코드"),
					fieldWithPath("data.content[].productDelivery.returnChargeDomestic").description("국내 반품 비용"),
					fieldWithPath("data.content[].productDelivery.returnExchangeAreaDomestic").description("국내 반품/교환 가능 지역"),



					fieldWithPath("data.content[].productDetailDescription.detailDescription").description("상품 상세 설명"),

					fieldWithPath("data.content[].productGroupImages[].id").description("상품 이미지 ID"),
					fieldWithPath("data.content[].productGroupImages[].productImageType").description("상품 이미지 타입 (예: MAIN, DETAIL)"),
					fieldWithPath("data.content[].productGroupImages[].imageUrl").description("상품 이미지 URL"),
					fieldWithPath("data.content[].productGroupImages[].originUrl").description("원본 이미지 URL"),
					fieldWithPath("data.content[].productGroupImages[].displayOrder").description("이미지 표시 순서"),


					fieldWithPath("data.content[].products[].id").description("상품 ID"),
					fieldWithPath("data.content[].products[].productGroupId").description("상품 그룹 ID"),
					fieldWithPath("data.content[].products[].quantity").description("상품 수량"),
					fieldWithPath("data.content[].products[].soldOut").description("품절 여부"),
					fieldWithPath("data.content[].products[].displayed").description("노출 여부"),
					fieldWithPath("data.content[].products[].additionalPrice").description("추가 금액"),
					fieldWithPath("data.content[].products[].deleted").description("삭제 여부"),

					// 옵션 정보
					fieldWithPath("data.content[].products[].options[]").description("옵션").optional(),
					fieldWithPath("data.content[].products[].options[].optionGroupId").description("옵션 그룹 ID"),
					fieldWithPath("data.content[].products[].options[].optionDetailId").description("옵션 상세 ID"),
					fieldWithPath("data.content[].products[].options[].optionName").description("옵션명"),
					fieldWithPath("data.content[].products[].options[].optionValue").description("옵션 값"),

					//브랜드 정보
					fieldWithPath("data.content[].brand.id").description("브랜드 ID"),
					fieldWithPath("data.content[].brand.brandName").description("브랜드 이름"),

					//카테고리 정보
					fieldWithPath("data.content[].categories[].id").description("카테고리 ID"),
					fieldWithPath("data.content[].categories[].name").description("카테고리 이름"),
					fieldWithPath("data.content[].categories[].depth").description("카테고리 깊이"),
					fieldWithPath("data.content[].categories[].parentCategoryId").description("부모 카테고리 ID"),
					fieldWithPath("data.content[].categories[].displayed").description("카테고리 노출 여부"),
					fieldWithPath("data.content[].categories[].targetGroup").description("카테고리 타겟 그룹 (예: KIDS)"),
					fieldWithPath("data.content[].categories[].categoryType").description("카테고리 타입 (예: BAG, CLOTHING)"),
					fieldWithPath("data.content[].categories[].path").description("카테고리 경로 (예: 1543,1544,1545)"),

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
