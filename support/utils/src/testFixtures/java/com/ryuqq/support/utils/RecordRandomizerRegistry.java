package com.ryuqq.support.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.ObjectCreationException;
import org.jeasy.random.ObjenesisObjectFactory;
import org.jeasy.random.api.RandomizerContext;

public class RecordRandomizerRegistry extends ObjenesisObjectFactory {

	private final EasyRandom easyRandom;

	public RecordRandomizerRegistry(EasyRandom easyRandom) {
		this.easyRandom = easyRandom;
	}

	@Override
	public <T> T createInstance(Class<T> type, RandomizerContext context) {
		if (type.isRecord()) {
			return createRandomRecord(type);
		}
		T instance = super.createInstance(type, context);
		initializeFinalFields(instance); // final 필드 초기화 추가
		return instance;
	}

	private <T> T createRandomRecord(Class<T> recordType) {
		RecordComponent[] recordComponents = recordType.getRecordComponents();
		Object[] randomValues = new Object[recordComponents.length];
		for (int i = 0; i < recordComponents.length; i++) {
			randomValues[i] = easyRandom.nextObject(recordComponents[i].getType());
		}
		try {
			return getCanonicalConstructor(recordType).newInstance(randomValues);
		} catch (Exception e) {
			throw new ObjectCreationException("Unable to create a random instance of recordType " + recordType, e);
		}
	}

	private <T> Constructor<T> getCanonicalConstructor(Class<T> recordType) {
		try {
			return recordType.getDeclaredConstructor(
				Arrays.stream(recordType.getRecordComponents())
					.map(RecordComponent::getType)
					.toArray(Class<?>[]::new)
			);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Invalid record definition: " + recordType, e);
		}
	}

	private <T> void initializeFinalFields(T instance) {
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())) {
				field.setAccessible(true);
				try {
					Object randomValue = easyRandom.nextObject(field.getType());
					field.set(instance, randomValue);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Unable to set value for final field: " + field.getName(), e);
				}
			}
		}
	}
}
