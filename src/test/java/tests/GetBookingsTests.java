package tests;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static services.EndpointPaths.Get_Bookings;

public class GetBookingsTests {

    @Test
    public void Test_Get_Bookings_200OK()
    {
        given().
                get(Get_Bookings)
                .then().
                statusCode(HttpStatus.SC_OK);
    }

}
