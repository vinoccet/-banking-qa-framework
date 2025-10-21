package com.vin.automation.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private final RequestSpecification spec;

    public ApiClient(String baseUri){
        this.spec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .build();
    }

    public RequestSpecification spec(){ return spec; }
}
