package com.vin.automation.api.services;

import com.vin.automation.api.payloads.TokenPayload;
import io.restassured.response.Response;

public class AuthService extends BaseService{
    public Response auth(String endpoint, TokenPayload tokenPayload){

        return this.doPost(endpoint,tokenPayload);
    }
}
