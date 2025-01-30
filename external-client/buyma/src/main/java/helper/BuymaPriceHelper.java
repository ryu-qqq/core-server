package helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ryuqq.core.domain.Money;

public class BuymaPriceHelper {

	/**
	 * 최종 가격을 계산합니다.
	 *
	 * @param currentPrice 세토프 판매가 (현재 가격 및 정가 포함).
	 * @param exchangeRate 환율 (엔/원).
	 * @return 최종 외부 몰 가격 (배송비 포함).
	 */

	public static BigDecimal calculateFinalPrice(BigDecimal currentPrice, BigDecimal exchangeRate) {

		Money currentMoney = Money.wons(currentPrice);

		BigDecimal normalizedExchangeRate = exchangeRate.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

		BigDecimal basePrice = calculateBasePrice(currentMoney, normalizedExchangeRate);

		BigDecimal baseWithShipping = basePrice.add(BigDecimal.valueOf(900));


		BigDecimal finalPrice = baseWithShipping.compareTo(BigDecimal.valueOf(16666)) > 0
			? calculateAdjustedPrice(currentMoney, normalizedExchangeRate)
			: basePrice;

		return ensureHundredsPlaceIs900(finalPrice);
	}

	/**
	 * 기본 가격 계산 로직.
	 *
	 * @param currentPrice 현재 판매가.
	 * @param exchangeRate 정규화된 환율.
	 * @return 기본 가격 (천 단위 반올림 적용).
	 */
	private static BigDecimal calculateBasePrice(Money currentPrice, BigDecimal exchangeRate) {
		return currentPrice
			.times(1.1)
			.divide(Money.wons(exchangeRate), 0, RoundingMode.CEILING)
			.divide(BigDecimal.valueOf(1000), RoundingMode.CEILING)
			.multiply(BigDecimal.valueOf(1000));
	}

	/**
	 * 조정된 가격 계산 로직.
	 * - 기준 초과(16666엔) 시, 추가 마진 적용.
	 *
	 * @param currentPrice 현재 판매가.
	 * @param exchangeRate 정규화된 환율.
	 * @return 조정된 가격 (천 단위 반올림 적용).
	 */
	private static BigDecimal calculateAdjustedPrice(Money currentPrice, BigDecimal exchangeRate) {
		return currentPrice
			.times(1.1)
			.divide(Money.wons(exchangeRate), 0, RoundingMode.CEILING)
			.multiply(BigDecimal.valueOf(1.1))
			.multiply(BigDecimal.valueOf(1.1))
			.divide(BigDecimal.valueOf(1000), RoundingMode.CEILING)
			.multiply(BigDecimal.valueOf(1000));
	}

	/**
	 * 최종 가격의 100자리 부분을 항상 900으로 보정합니다.
	 *
	 * @param value 조정 전 최종 가격.
	 * @return 100자리 부분이 900으로 설정된 최종 가격.
	 */
	private static BigDecimal ensureHundredsPlaceIs900(BigDecimal value) {
		return value.divide(BigDecimal.valueOf(1000), 0, RoundingMode.DOWN)
			.multiply(BigDecimal.valueOf(1000))
			.add(BigDecimal.valueOf(900));
	}

}
