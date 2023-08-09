package day6;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ApiCalls;
import utilities.BaseUrl;
import utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C11_getRequest {

    /*
    De-serialization
    response -> json body {.....data....}

    json -> java object   = de-serialization

    Map -> HashMap<key, value>


     */

    @Test
    public void testWithHashMap(){

        HashMap<String,Object> expectedData = TestData.getBookingData();
        Response response = given()
                .when()
                .get(BaseUrl.herokuappUserId(140));

        HashMap<String,Object> actualData = response.as(HashMap.class); // De-serialization is here

        // Assertion

        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
        Assert.assertEquals(expectedData.get("checkin"),actualData.get("checkin"));
        Assert.assertEquals(expectedData.get("checkout"),actualData.get("checkout"));


    }

    @Test
    public void bookingTest(){

        ApiCalls.deserializationBooking(95,200,"John","Smith",111.0,true,"2018-01-01","2019-01-01");

    }
    @Test
    public void bookingTest01(){

        ApiCalls.deserializationBooking(131,200,"John","Smith",111.0,true,"2018-01-01","2019-01-01");

    }
}
