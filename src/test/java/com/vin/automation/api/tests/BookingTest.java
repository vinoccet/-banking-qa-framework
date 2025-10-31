package com.vin.automation.api.tests;


import com.vin.automation.api.payloads.Booking;
import com.vin.automation.api.payloads.BookingDates;
import com.vin.automation.api.payloads.TokenPayload;
import com.vin.automation.api.services.AuthService;
import com.vin.automation.api.services.BookingService;
import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest {
    @Test
    public void testGetBooking200() {
        BookingService bookingService = new BookingService();
        Response response = bookingService.getBooking("booking/59");
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Jane");
    }

    @Test
    public void deleteBookingTest() {
        /*
        curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Cookie: token=abc123'
         */

        // get the token
        // https://restful-booker.herokuapp.com/auth
        // before deleting we can create booking and then get that id and delete
        AuthService authService = new AuthService();
        BookingService bookingService = new BookingService();
        Booking booking = new Booking();
        Response createResponse = bookingService.createBooking("booking", booking);
        String createdBookingId = createResponse.jsonPath().getString("bookingid");
        TokenPayload tokenPayload = new TokenPayload("admin", "password123");
        Response authResponse = authService.auth("auth", tokenPayload);
        String token = authResponse.jsonPath().getString("token");
        System.out.println(token);
        Response deleteResponse = bookingService.deleteBooking("booking/" + createdBookingId, token);
        Assert.assertEquals(deleteResponse.statusCode(), 201);


    }
}
