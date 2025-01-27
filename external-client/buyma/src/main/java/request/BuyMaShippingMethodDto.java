package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaShippingMethodDto(
	@JsonProperty("shipping_method_id")
	int shippingMethodId
) {
}
