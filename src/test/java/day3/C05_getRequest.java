package day3;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_getRequest {
    /*
    *** TC06:Send a GET Request to https://reqres.in/api/users/1
             and verify:
             The status code is 200
             The content type is application/json; Charset=utf-8
             email is george.bluth@reqres.in
             first_name is George
             last_name is Bluth
             avatar is https://reqres.in/img/faces/1-image.jpg
             url is https://reqres.in/#support-heading
             text is To keep ReqRes free, contributions towards server costs are appreciated!
     */

    //1- End-point and request body
    String url = "https://reqres.in/api/users/1";

//2- Expected data
    // no need this step

//3- Send our request and save our response

    Response response = given().when().get(url);

//Assertion
    @Test
    public void herokuAppJunitAssert() {

        response.then().assertThat().statusCode(200).contentType("application/json; Charset=utf-8")
                .body("data.email", Matchers.equalTo("george.bluth@reqres.in"),
                "data.firstname",Matchers.equalTo("George"),
                        "data.lastname",Matchers.equalTo("Bluth"),
                        "data.avatar",Matchers.equalTo("https://reqres.in/img/faces/1-image.jpg"),
                        "support.url",Matchers.equalTo("https://reqres.in/#support-heading"),
                        "support.text",Matchers.equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
