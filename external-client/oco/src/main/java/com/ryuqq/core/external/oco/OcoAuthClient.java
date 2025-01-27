package com.ryuqq.core.external.oco;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ryuqq.core.external.oco.request.OcoTokenRequestDto;
import com.ryuqq.core.external.oco.response.OcoResponse;
import com.ryuqq.core.external.oco.response.OcoTokenResponseDto;

@FeignClient(name = "ocoAuthClient", url = "${oco.host-url}", configuration = OcoAuthConfig.class)
public interface OcoAuthClient {

	@PostMapping(value = "/auth/authentication.do")
	OcoResponse<OcoTokenResponseDto> fetchToken(@RequestBody OcoTokenRequestDto requestDto);

}
