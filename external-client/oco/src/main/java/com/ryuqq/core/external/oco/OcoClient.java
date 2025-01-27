package com.ryuqq.core.external.oco;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ryuqq.core.external.oco.request.OcoOptionUpdateRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductInsertRequestWrapperDto;
import com.ryuqq.core.external.oco.request.OcoProductOptionDeleteRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductPriceUpdateRequestDto;
import com.ryuqq.core.external.oco.request.OcoProductUpdateRequestWrapperDto;
import com.ryuqq.core.external.oco.response.OcoProductInsertResponseDto;
import com.ryuqq.core.external.oco.response.OcoProductUpdateResponseDto;
import com.ryuqq.core.external.oco.response.OcoResponse;

@FeignClient(name = "ocoClient", url = "${oco.host-url}", configuration = OcoConfig.class)
public interface OcoClient {

	@PostMapping(value ="/product/add.do")
	ResponseEntity<OcoResponse<OcoProductInsertResponseDto>> insertProduct(@RequestBody OcoProductInsertRequestWrapperDto requestDto);

	@PostMapping(value ="/product/update.do")
	ResponseEntity<OcoResponse<OcoProductInsertResponseDto>> updateProduct(@RequestBody OcoProductUpdateRequestWrapperDto requestDto);

	@PostMapping(value ="/product/price/update.do")
	ResponseEntity<OcoResponse<OcoProductUpdateResponseDto>> updatePrice(@RequestBody OcoProductPriceUpdateRequestDto requestDto);

	@PostMapping(value ="/product/option/update.do")
	ResponseEntity<OcoResponse<OcoProductInsertResponseDto>> updateOption(@RequestBody OcoOptionUpdateRequestDto requestDto);

	@PostMapping(value ="/product/option/delete.do")
	ResponseEntity<OcoResponse<OcoProductUpdateResponseDto>> deleteOption(@RequestBody OcoProductOptionDeleteRequestDto requestDto);



}
