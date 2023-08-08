package day4;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.BaseUrlSpec;
import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class C07_getRequest extends BaseUrlSpec {

    @Test
    public void getReqresAll(){

        /*
         *** TC07:Send a GET Request to https://reqres.in/api/users
              and verify:
              The status code is 200
              The content type is application/json; charset=utf-8
              Verify that the following exists in data
              email is tracey.ramos@reqres.in
              first_name is Tracey
              last_name is Ramos
              avatar is https://reqres.in/img/faces/6-image.jpg

             {
                "id": 6,
                "email": "tracey.ramos@reqres.in",
                "first_name": "Tracey",
                "last_name": "Ramos",
                "avatar": "https://reqres.in/img/faces/6-image.jpg"
             }
         */
        // 1- End-point and request body

        specReqresIn.pathParams("pp1","api","pp2","users");

        // 2- Expected data

        // 3- Send the request and save the response

        Response response = given().spec(specReqresIn)
                .when()
                .get("/{pp1}/{pp2}");

        // 4- Assertions

        response
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("data[5].id", Matchers.equalTo(6),
                        "data[5].email",equalTo("tracey.ramos@reqres.in"),
                        "data[5].first_name",equalTo("Tracey"),
                        "data[5].last_name", equalTo("Ramos"),
                        "data[5].avatar",equalTo("https://reqres.in/img/faces/6-image.jpg"));
    }
    @Test
    public void getBooking(){
        /*
        *** TC08:Send a GET Request to https://restful-booker.herokuapp.com/booking/100
     and verify:
     The status code is 200
     The content type is application/json; charset=utf-8
     firstname is John
     lastname is Smith
     totalprice is 111
     depositpaid is true
     bookingdates (checkin) is 2018-01-01
     bookingdates (checout) is 2019-01-01

         {
       "firstname": "Jane",
       "lastname": "Doe",
       "totalprice": 111,
       "depositpaid": true,
       "bookingdates": {
           "checkin": "2018-01-01",
           "checkout": "2019-01-01"
       },
       "additionalneeds": "Extra pillows please"
   }
         */
        // 1- End-point  request body

        specHerokuapp.pathParams("pp1","booking","pp2","2051");

        // 2- Expected data

        // 3- Send request and save response

        Response response= given().spec(specHerokuapp)
                .when()
                .get("/{pp1}/{pp2}");

        // 4- Assertion

        response
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("firstname",equalTo("Josh"),
                        "lastname",equalTo("Allen"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds",equalTo("super bowls")
                );




    }
}
