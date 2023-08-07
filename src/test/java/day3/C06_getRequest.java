package day3;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.BaseUrlSpec;

import static io.restassured.RestAssured.given;

public class C06_getRequest extends BaseUrlSpec {
     /*
    *** TC07:Send a GET Request to https://reqres.in/api/users/1
              and verify:
              The status code is 200
              The content type is application/json; Charset=utf-8
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

//1- End-point and request body
    @Test
    public void getReqres() {

        specReqresIn.pathParams("pp1", "api", "pp2", "users", "pp3", "1");


//2- Expected data
        // no need this step

//3- Send our request and save our response
        Response response = given().spec(specReqresIn).when().get("{pp1}/{pp2}/{pp3}");

//Assertion

        response.then().assertThat().statusCode(200).contentType("application/json; Charset=utf-8").body("data.email", Matchers.equalTo("george.bluth@reqres.in"),
                "data.first_name",Matchers.equalTo("George"),
                "data.last_name",Matchers.equalTo("Bluth"),
                "data.avatar",Matchers.equalTo("https://reqres.in/img/faces/1-image.jpg"));
    }

}
