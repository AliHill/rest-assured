Feature: Get booking by specific Id

Given a booking id of 1 exists
When a user wants to retrieve their booking information
Then the request should respond with a 200 OK response code
And the response body should contain their booking details