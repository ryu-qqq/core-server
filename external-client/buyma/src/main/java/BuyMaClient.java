import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import request.BuyMaProductInsertRequestWrapperDto;
import request.BuyMaProductStockUpdateRequestWrapperDto;
import response.BuyMaResponse;

@FeignClient(name = "buyMaClient", url = "${buyMa.host-url}", configuration = BuyMaConfig.class)
public interface BuyMaClient {

	@PostMapping("/api/v1/products.json")
	ResponseEntity<Object> insertProduct(@RequestBody BuyMaProductInsertRequestWrapperDto product);

	@PostMapping("/api/v1/products/variants.json")
	ResponseEntity<BuyMaResponse<?>> updateStock(@RequestBody BuyMaProductStockUpdateRequestWrapperDto variant);

}
