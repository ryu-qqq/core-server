package com.ryuqq.core.external.oco.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OcoResponseStatus(
        int shop_acid,
        String returnMessage,
        int statusCode
) {
}
