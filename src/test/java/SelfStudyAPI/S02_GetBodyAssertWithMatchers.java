package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class S02_GetBodyAssertWithMatchers {

    /*
     "https://jsonplaceholder.typicode.com/posts/44" e Get yolla ve donen response da;
     statusCode 200, contentType applicaiton/json,
     response body de bulunan userId nin 5 oldugunu,
     response body de bulunan title in optio dolor molestias sit oldugunu test edin
     Matchers ile body testi (key,value)
     */


    @Test
    public void bodyAssert(){

        String url = "https://jsonplaceholder.typicode.com/posts/44";

        Response response = given()
                                    .when()
                                    .get(url);

        response.prettyPrint(); // Cagirdik ve manual olarak test ettik.

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", Matchers.equalTo(5))
                .body("title",Matchers.equalTo("optio dolor molestias sit"));



    }
}
