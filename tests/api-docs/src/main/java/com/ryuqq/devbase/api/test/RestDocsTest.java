package com.ryuqq.devbase.api.test;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsTest {

	protected MockMvcRequestSpecification mockMvc;

	private RestDocumentationContextProvider restDocumentation;

	@BeforeEach
	public void setUp(RestDocumentationContextProvider restDocumentation) {
		this.restDocumentation = restDocumentation;
	}

	protected MockMvcRequestSpecification given() {
		return mockMvc;
	}

	protected MockMvcRequestSpecification mockController(Object controller) {
		MockMvc mockMvc = createMockMvc(controller);
		return RestAssuredMockMvc.given().mockMvc(mockMvc);
	}

	private MockMvc createMockMvc(Object controller) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper());

		return MockMvcBuilders.standaloneSetup(controller)
			.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
				.operationPreprocessors()
				.withRequestDefaults(Preprocessors.prettyPrint())
				.withResponseDefaults(Preprocessors.prettyPrint()))
			.alwaysDo(MockMvcResultHandlers.print())
			.defaultRequest(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
			.defaultRequest(MockMvcRequestBuilders.get("/").contentType(MediaType.APPLICATION_JSON))
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.setMessageConverters(converter)
			.build();
	}

	private ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
	}

	protected FieldDescriptor[] statusMsg() {
		return new FieldDescriptor[] {
			fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태"),
			fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
		};
	}

	protected OperationRequestPreprocessor requestPreprocessor() {
		return Preprocessors.preprocessRequest(
			Preprocessors.modifyUris().scheme("http").host("com.ryuqq.devbase").removePort(),
			Preprocessors.prettyPrint());
	}

	protected OperationResponsePreprocessor responsePreprocessor() {
		return Preprocessors.preprocessResponse(Preprocessors.prettyPrint());
	}
}
