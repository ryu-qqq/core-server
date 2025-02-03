package com.ryuqq.core.external.buyma;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ryuqq.core.external.buyma.request.BuyMaProductInsertRequestWrapperDto;
import com.ryuqq.core.external.buyma.request.BuyMaProductStockUpdateRequestWrapperDto;
import com.ryuqq.core.external.buyma.response.BuyMaProductInsertResponseDto;



@FeignClient(name = "buyMaClient", url = "${buyMa.host-url}", configuration = BuyMaConfig.class)
public interface BuyMaClient {

	@PostMapping("/api/v1/products.json")
	ResponseEntity<BuyMaProductInsertResponseDto> insertProduct(@RequestBody BuyMaProductInsertRequestWrapperDto product);

	@PostMapping("/api/v1/products/variants.json")
	ResponseEntity<BuyMaProductInsertResponseDto> updateStock(@RequestBody BuyMaProductStockUpdateRequestWrapperDto variant);

}
