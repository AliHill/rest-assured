package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static services.EndpointPaths.Get_Booking_By_Id;

import static org.hamcrest.Matchers.equalTo;


public class GetBookingByIdStepDefinitions {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Given("a booking id of 1 exists")
    public void a_booking_exists_with_bookingid(String bookingId) {
        request = given().pathParam("bookingId", bookingId);
    }

    @When("a user wants to retrieve their booking information")
    public void a_user_retrieves_booking_information() {
        response = request.when().get(Get_Booking_By_Id);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is (\\d+)")
    public void verify_status_code(int statusCode) {
        json = response.then().statusCode(statusCode);
    }

    @And("response includes the following$")
    public void response_equals(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            } else {
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }


    }
}