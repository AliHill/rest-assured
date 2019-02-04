package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import responses.GetBookingByIdResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static services.EndpointPaths.Get_Booking_By_Id;

public class GetBookingByIdTests {

    private static final String bookingId = "1";

    @Test
    public void Test_Get_ByBookingId_200OK() {
        given().
                pathParam("bookingId", bookingId).
                when().
                get(Get_Booking_By_Id)
                .then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void Test_Get_ByBookingId_SchemaValidation() {
        given().
                pathParam("bookingId", bookingId).
                when().
                get(Get_Booking_By_Id).
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonschemas/GetBookingById.json"));
    }

    @Test
    public void Test_Get_ByBookingId_InvalidBookingId_404NotFound() {
        given().
                pathParam("bookingId", "thisshouldnotexist").
                when().
                get(Get_Booking_By_Id).
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Ignore("Bug XXXXX has been raised - API is not redirecting and is making requests over HTTP")
    public void Test_Get_ByBookingId_RequestOverHttp_302Redirect() {
        String requestOverHttp = "http://restful-booker.herokuapp.com/booking/1";

        given().
                when().
                get(requestOverHttp)
                .then().
                statusCode(HttpStatus.SC_MOVED_TEMPORARILY);
    }

    @Test
    public void Test_Get_ByBookingId_ValidateHeaders() {

        String serverName = "Cowboy";

        given().
                pathParam("bookingId", bookingId).
                when().
                get(Get_Booking_By_Id).then().
                assertThat().
                header("Server", serverName);
    }

    @Test
    public void Test_Get_ByBookingId_ValidateResponseBody() {
        ResponseBody response = given().
                pathParam("bookingId", bookingId).
                when().
                get(Get_Booking_By_Id);

        JsonPath jsonPathEvaluator = response.jsonPath();

        String firstName = jsonPathEvaluator.get("firstname");

        Assert.assertEquals("Sally", firstName);
    }

        @Test
        public void Test_Get_ByBookingId_ValidateResponseObject() {

        var expectedFirstName = "Mary";

        GetBookingByIdResponse response = given().
                    pathParam("bookingId", bookingId).
                    when().
                    get(Get_Booking_By_Id).
                    as(GetBookingByIdResponse.class);

            Assert.assertEquals(expectedFirstName, response.firstname );
    }
}