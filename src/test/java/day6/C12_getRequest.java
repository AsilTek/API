package day6;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import utilities.BaseUrl;
import utilities.TestData;

import static io.restassured.RestAssured.given;

public class C12_getRequest {

    @Test
    public void bookingTestSerialization(){

     /*
     Java -> JSON = Serialization
      */

        JSONObject expectedData = TestData.getBookingDataJSON();

        Response response = given()
                .when()
                .get(BaseUrl.herokuappUserId(7));

        JSONObject actualData = new JSONObject(response.getBody().asString()); //Serialization JSON to JSON



    }
}
