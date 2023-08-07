package day3;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/*
*** TC04:Send a GET Request to https://restful-booker.herokuapp.com/booking/125
    and verify:
    The status code is 200
    The content type is application/json; Charset=utf-8
    firstname is John
    lastname is Smith
    totalprice is 111
    depositpaid is true
    bookingdates (checkin) is 2018-01-01
    bookingdates (checout) is 2019-01-01
 */
public class C04_getRequest {

//1- End-point and request body
    String url = "https://restful-booker.herokuapp.com/booking/125";

//2- Expected data
    // no need this step

//3- Send our request and save our response

    Response response = given().when().get(url);

//Assertion
@Test
public void herokuAppJunitAssert(){

    response
            .then()
            .statusCode(200)
            .contentType("application/json; Charset=utf-8")
            .body("firstname", Matchers.equalTo("Josh"),"lastname", Matchers.equalTo("Allen"),
                    "totalprice", Matchers.equalTo(111),"depositpaid", Matchers.equalTo(true),
                    "bookingdates.checkin", Matchers.equalTo("2018-01-01"),"bookingdates.checkout", Matchers.equalTo("2019-01-01"));



}

}
