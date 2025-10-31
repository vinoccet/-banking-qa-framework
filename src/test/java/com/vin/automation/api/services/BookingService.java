package com.vin.automation.api.services;

import com.vin.automation.api.payloads.Booking;
import io.restassured.response.Response;

public class BookingService extends BaseService {
    public Response getBooking(String endpoint) {
        return this.doGet(endpoint);
    }

    public Response createBooking(String endpoint, Booking booking) {
        return this.doPost(endpoint, booking);
    }

    public Response deleteBooking(String endpoint, String token) {
        this.setAuth(token);
        return this.doDelete(endpoint);
    }
}

