package com.ryuqq.support.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.ObjectCreationException;
import org.jeasy.random.ObjenesisObjectFactory;
import org.jeasy.random.api.RandomizerContext;

public class RecordRandomizerRegistry extends ObjenesisObjectFactory {

    private final EasyRandom easyRandom;

    public RecordRandomizerRegistry(EasyRandomParameters parameters) {
        this.easyRandom = new EasyRandom(parameters);
    }


    @Override
    public <T> T createInstance(Class<T> type, RandomizerContext context) {
        if (type.isRecord()) {
            return createRandomRecord(type);
        }
        return super.createInstance(type, context);
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
}
