package com.remwaste.step_definitions;

import com.remwaste.utilities.ConfigurationReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HerokuAppApiSteps {

    /***
     This class contains API step definitions for interacting with the HerokuApp RESTful service.
     It includes methods to perform API operations such as GET, POST, PUT, DELETE requests,
     and corresponding validations, using tools like Rest Assured.
     These steps are used in Cucumber feature files to automate API testing scenarios.
     ***/
    protected RequestSpecification specHerokuApp;
    Response response;


    @Given("the user sends a POST request to the login endpoint with credentials")
    public void the_user_sends_a_post_request_to_the_login_endpoint_with_credentials() {

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParam("pp1", "auth");

        JSONObject requestBodyAuth = new JSONObject();
        requestBodyAuth.put("username", "admin");
        requestBodyAuth.put("password", "password123");

        response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .when().body(requestBodyAuth.toString())
                .post("/{pp1}");

    }

    @Given("the response body should contain an authentication token")
    public void the_response_body_should_contain_an_authentication_token() {

        response.then().assertThat()
                .body("token", Matchers.notNullValue());

    }

    @Then("the response status code should be 200")
    public void the_response_status_code_should_be_200() {

        response.then().assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8");

    }

    @Given("the user sends a GET request to the bookings endpoint")
    public void the_user_sends_a_get_request_to_the_bookings_endpoint() {

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParam("pp1", "booking");

        response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .when().get("/{pp1}");

    }

    @Then("the response body should contain a list of booking IDs")
    public void the_response_body_should_contain_a_list_of_booking_i_ds() {

        JsonPath responseJsonPath = response.jsonPath();
        List<Integer> bookingIds = responseJsonPath.getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty());

    }


    @Given("the user sends a GET request by using id")
    public void the_user_sends_a_get_request_by_using_id() {

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParams("pp1", "booking", "pp2", "1");

        response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .when().get("/{pp1}/{pp2}");

    }

    @Then("the response body should contain first name and last name information")
    public void the_response_body_should_contain_first_name_and_last_name_information() {

        response.then().assertThat()
                .body("firstname", Matchers.notNullValue(),
                        "lastname", Matchers.notNullValue());

    }

    @Given("the user sends a POST request with the following data")
    public void the_user_sends_a_post_request_with_the_following_data(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String requestBody = String.format("""
            {
              "firstname": "%s",
              "lastname": "%s",
              "totalprice": %s,
              "depositpaid": %s,
              "bookingdates": {
                "checkin": "%s",
                "checkout": "%s"
              },
              "additionalneeds": "%s"
            }
            """,
                data.get("firstname"),
                data.get("lastname"),
                data.get("totalprice"),
                data.get("depositpaid"),
                data.get("checkin"),
                data.get("checkout"),
                data.get("additionalneeds")
        );

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParam("pp1", "booking");

        response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .when().body(requestBody)
                .post("/{pp1}");

    }

    @Then("the response should contain the same booking details")
    public void the_response_should_contain_the_same_booking_details() {

        response.then()
                .body("booking.firstname", equalTo("Olivia"))
                .body("booking.lastname", equalTo("Wilson"))
                .body("booking.totalprice", equalTo(444))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"));

        response.prettyPrint();

    }

    @Given("the user updates an existing booking with the following data")
    public void the_user_updates_an_existing_booking_with_the_following_data(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", data.get("checkin"));
        bookingDates.put("checkout", data.get("checkout"));

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", data.get("firstname"));
        booking.put("lastname", data.get("lastname"));
        booking.put("totalprice", Integer.parseInt(data.get("totalprice")));
        booking.put("depositpaid", Boolean.parseBoolean(data.get("depositpaid")));
        booking.put("bookingdates", bookingDates);
        booking.put("additionalneeds", data.get("additionalneeds"));

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParams("pp1", "booking","pp2","59");

        response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "password123").when().body(booking)
                .put("/{pp1}/{pp2}");

    }

    @Then("the booking should updated")
    public void the_booking_should_updated() {

        response.then().assertThat()
                .body("firstname", equalTo("Olivia1"))
                .body("lastname", equalTo("Wilson1"))
                .body("totalprice", equalTo(4441))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));

    }

    @Given("the user deletes the existing booking")
    public void the_user_deletes_the_existing_booking() {

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParams("pp1", "booking","pp2","87");

        response = given().spec(specHerokuApp)
                .auth().preemptive().basic("admin", "password123")
                .when()
                .delete("/{pp1}/{pp2}");

    }
    @Then("Then the user should verify that the booking is successfully deleted")
    public void then_the_user_should_verify_that_the_booking_is_successfully_deleted()  {

        response.then().statusCode(201);

    }

    @Given("the user tries to update an existing booking with the following data with invalid token")
    public void the_user_tries_to_update_an_existing_booking_with_the_following_data_with_invalid_token(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", data.get("checkin"));
        bookingDates.put("checkout", data.get("checkout"));

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", data.get("firstname"));
        booking.put("lastname", data.get("lastname"));
        booking.put("totalprice", Integer.parseInt(data.get("totalprice")));
        booking.put("depositpaid", Boolean.parseBoolean(data.get("depositpaid")));
        booking.put("bookingdates", bookingDates);
        booking.put("additionalneeds", data.get("additionalneeds"));

        specHerokuApp = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("baseUri")).build();
        specHerokuApp.pathParams("pp1", "booking","pp2","68");

        response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .auth().preemptive().basic("admin1222", "password123").when().body(booking)
                .put("/{pp1}/{pp2}");

    }

    @Then("the response status code should be 403")
    public void the_response_status_code_should_be_403( ) {

        response.then().assertThat().statusCode(403);

    }

}
