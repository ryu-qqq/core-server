package com.ryuqq.support.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.ExclusionPolicy;
import org.jeasy.random.api.RandomizerContext;
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
			.exclusionPolicy(new ExclusionPolicy() {
				@Override
				public boolean shouldBeExcluded(Field field, RandomizerContext context) {
					boolean isFinal = Modifier.isFinal(field.getModifiers());
					boolean isSpecificField = field.getName().equals("testGenerate");
					return isFinal || isSpecificField;
				}

				@Override
				public boolean shouldBeExcluded(Class<?> aClass, RandomizerContext randomizerContext) {
					return false;
				}
			});

		parameters.objectFactory(new RecordRandomizerRegistry(parameters));

		easyRandom = new EasyRandom(parameters);

	}


	public static EasyRandom getEasyRandom() {
		return easyRandom;
	}

}
