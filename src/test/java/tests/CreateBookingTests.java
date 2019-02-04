package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import requests.BookingDates;
import requests.CreateBookingRequest;

import static io.restassured.RestAssured.given;
import static services.EndpointPaths.Create_Booking;

public class CreateBookingTests {

    private Gson gson = new Gson();
    private CreateBookingRequest bookingRequest = new CreateBookingRequest();

    public CreateBookingRequest defaultRequest()
    {
        bookingRequest.bookingdates = new BookingDates();

        this.bookingRequest.firstname = "Ali";
        this.bookingRequest.lastname = "Hill";
        this.bookingRequest.totalprice = 100;
        this.bookingRequest.depositpaid = true;
        this.bookingRequest.bookingdates.checkin = "2018-01-01";
        this.bookingRequest.bookingdates.checkout = "2018-01-02";
        this.bookingRequest.additionalneeds = "Hotdogs";

        return bookingRequest;
    }

    @Test
    public void Test_CreateBooking_200OK() {

        var preRequest = defaultRequest();
        String request = gson.toJson(preRequest);

        given().contentType(ContentType.JSON).
                body(request).
                when().
                post(Create_Booking).
                then().
                statusCode(HttpStatus.SC_OK);
    }

}
