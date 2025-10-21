package com.vin.automation.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vin.automation.model.CustomerData;

import java.io.InputStream;
import java.util.List;

public final class DataLoaders {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private DataLoaders(){}

    public static List<CustomerData> fromJsonArray(String classpath) {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(classpath)) {
            if (in == null) throw new IllegalArgumentException("Not found on classpath: " + classpath);
            return MAPPER.readValue(in, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
