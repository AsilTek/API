package day4;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utilities.BaseUrlSpec;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class C08_getRequest extends BaseUrlSpec{

    @Test
    public void userId1ReqresIn() {
/*
*** TC08:Send a GET Request to https://reqres.in/api/users
              and verify:
              The status code is 200
              The content type is application/json; charset=utf-8
              email is george.bluth@reqres.in
              first_name is George
              last_name is Bluth
              avatar is https://reqres.in/img/faces/1-image.jpg
          {
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
          },
          * We will use jsonpath to assert the body
 */

        // 1- End-point and request body

        specReqresIn.pathParams("pp1", "api", "pp2", "users");

        // 2- Expected data

        // 3- Send request and save response

        Response response = given().spec(specReqresIn)
                .when()
                .get("/{pp1}/{pp2}");

        // 4- Assertions

//        response.then()
//                .assertThat()
//                .statusCode(200)
//                .contentType("application/json; charset=utf-8");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getList("data.email"));
        System.out.println(jsonPath.getList("data.first_name"));
        System.out.println(jsonPath.getList("data.last_name"));

        assertEquals(1, jsonPath.getInt("data[0].id"));
        assertEquals("george.bluth@reqres.in", jsonPath.getString("data[0].email"));
        assertEquals("George", jsonPath.getString("data[0].first_name"));
        assertEquals("Bluth", jsonPath.getString("data[0].last_name"));
        assertEquals("https://reqres.in/img/faces/1-image.jpg", jsonPath.getString("data[0].avatar"));

    }

    @Test

    public void postHerokuapp(){

          /*
        https://restful-booker.herokuapp.com/booking

        {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 111,
            "depositpaid" : false,
            "bookingdates" : {
            "checkin" : "2023-01-10",
            "checkout" : "2023-01-20"
                                        },
            "additionalneeds" : "wi-fi"
           }

           Validate for response
        status code 200
        content type application/json
        "firstname" "Ahmet"
        "lastname" "Bulut"
        "totalprice" 4444
        "depositpaid" false
        "checkin"  "2023-01-10",
         "checkout"  "2023-01-20"
        "additionalneeds" wi-fi
         */


        //1- End-point and request body

        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2023-01-10");
        bookingdates.put("checkout","2023-01-20");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",111);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingdates);
        requestBody.put("additionalneeds","wi-fi");

        // 2- Expected data

        // 3- send the request and save the response

        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON) // We have to send the content type
                .when().body(requestBody.toString())
                .post("/{pp1}");

        // 4- Assertions

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("booking.firstname", equalTo("Ahmet"),
                        "booking.lastname",equalTo("Bulut"),
                        "booking.totalprice",equalTo(111),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2023-01-10"),
                        "booking.bookingdates.checkout",equalTo("2023-01-20"));
    }
}