package com.vin.automation.api.services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Objects;

public class BaseService {
    public static final String BASEURL = "https://restful-booker.herokuapp.com/";
    private RequestSpecification requestSpecification;

    public BaseService() {
        requestSpecification = buildRequest(null);
    }

    public RequestSpecification buildRequest(String token) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(BASEURL)
                .setContentType(ContentType.JSON);
        if (Objects.nonNull(token)) {
            requestSpecBuilder.addHeader("Cookie", "token=" + token);
        }
        return requestSpecBuilder.build();

    }

    public void setAuth(String token) {
        requestSpecification = buildRequest(token);
    }

    protected Response doGet(String endpoint) {
        return RestAssured.given()
                .spec(requestSpecification)
                .when().get(endpoint).then().extract().response();
    }

    protected Response doPost(String endpoint, Object body) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(body).when().post(endpoint).then().extract().response();
    }


    protected Response doDelete(String endpoint) {
        return RestAssured.given()
                .spec(requestSpecification).when()
                .delete(endpoint).then().extract().response();
    }
}
