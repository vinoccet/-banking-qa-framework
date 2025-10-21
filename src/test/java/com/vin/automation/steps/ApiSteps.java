package com.vin.automation.steps;

import com.vin.automation.api.ApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class ApiSteps {
    private ApiClient client;
    private Response response;

    @Given("the public API {string}")
    public void the_public_api(String base){
        client = new ApiClient(base);
    }

    @When("I GET {string}")
    public void i_get(String path){
        response = RestAssured.given().spec(client.spec()).get(path);
    }

    @Then("response status is {int}")
    public void response_status(int code){
        Assert.assertEquals(response.statusCode(), code, "Unexpected status code");
    }

    @Then("field {string} is not empty")
    public void field_not_empty(String jsonPath){
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value, "Expected non-null field at " + jsonPath);
    }
}
