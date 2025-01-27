package com.ryuqq.core.external.sellic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ryuqq.core.external.sellic.request.SellicProductInsertRequestDto;
import com.ryuqq.core.external.sellic.request.SellicProductStockUpdateWrapperDto;
import com.ryuqq.core.external.sellic.response.SellicResponse;

@FeignClient(name = "sellicClient", url = "${sellic.host-url}", configuration = SellicConfig.class)
public interface SellicClient {

	@PostMapping("/set_product")
	ResponseEntity<SellicResponse> insertProduct(@RequestBody SellicProductInsertRequestDto requestDto);

	@PostMapping("/edit_product")
	ResponseEntity<SellicResponse> updateProduct(@RequestBody SellicProductInsertRequestDto requestDto);

	@PostMapping(value ="/edit_stockArr")
	ResponseEntity<SellicResponse> updateStock(@RequestBody SellicProductStockUpdateWrapperDto requestDto);

}
