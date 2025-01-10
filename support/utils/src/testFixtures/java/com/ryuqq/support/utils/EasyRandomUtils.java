package com.ryuqq.support.utils;

import java.math.BigDecimal;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.BigDecimalRangeRandomizer;

public class EasyRandomUtils {

	private final static EasyRandom easyRandom;

	static {

		EasyRandomParameters parameters = new EasyRandomParameters()
			.randomize(Long.class, new LongRangeRandomizer(1, 100))
			.randomize(Integer.class, new IntegerRangeRandomizer(1, 100))
			.randomize(String.class, new StringRandomizer(5))
			.randomize(BigDecimal.class, new BigDecimalRangeRandomizer(0.0, 1000000.0)) // 0부터 시작
			.randomizationDepth(3)
			.collectionSizeRange(1, 2)
			.objectFactory(new RecordRandomizerRegistry(new EasyRandom()));


		easyRandom = new EasyRandom(parameters);
	}

	private static EasyRandom getEasyRandom() {
		return easyRandom;
	}

	public static <T> T getRandom(Class<T> clazz) {
		return getEasyRandom().nextObject(clazz);
	}

}
