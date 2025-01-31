package com.ryuqq.core.external.oco;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.external.ExternalSiteException;
import com.ryuqq.core.external.oco.request.OcoTokenRequestDto;
import com.ryuqq.core.external.oco.response.OcoResponse;
import com.ryuqq.core.external.oco.response.OcoTokenResponseDto;


@Component
public class OcoAuthManager {

	private final OcoAuthClient ocoAuthClient;

	@Value("${oco.id}")
	private String id;

	@Value("${oco.password}")
	private String password;

	@Value("${oco.api-key}")
	private String apiKey;

	private volatile String token;
	private volatile Instant expiryTime;

	public OcoAuthManager(OcoAuthClient ocoAuthClient) {
		this.ocoAuthClient = ocoAuthClient;
	}

	public String getToken() {
		if (token == null || isTokenExpired()) {
			synchronized (this) {
				// 이중 체크 락킹
				if (token == null || isTokenExpired()) {
					fetchToken();
				}
			}
		}
		return token;
	}

	private boolean isTokenExpired() {
		return expiryTime == null || Instant.now().isAfter(expiryTime);
	}


	private void fetchToken() {
		try {
			OcoTokenRequestDto request = new OcoTokenRequestDto(id, password);
			OcoResponse<OcoTokenResponseDto> response = ocoAuthClient.fetchToken(request);

			if (response != null && response.apiResult() != null) {
				this.token = response.apiResult().token();
				this.expiryTime = Instant.now().plusSeconds(72000);
			} else {
				throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR, "Failed to fetch token from OCO - Invalid response");
			}
		} catch (Exception e) {
			throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  "Error fetching token from OCO");
		}
	}

	public synchronized void refreshToken() {
		this.token = null;
		fetchToken();
	}

}
