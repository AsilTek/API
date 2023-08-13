package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class S05_PostRequestJsonPath {

    @Test
    public void test01(){

        JSONObject innerBookingDates = new JSONObject();

        innerBookingDates.put("checkin","2021-06-01");
        innerBookingDates.put("checkout","2021-06-10");


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstname","Ali");
        jsonObject.put("lastname","Bak");
        jsonObject.put("totalprice",500);
        jsonObject.put("depositpaid",false);
        jsonObject.put("additionalneeds","wi-fi");
        jsonObject.put("bookingdates",innerBookingDates);

        String url = "https://restful-booker.herokuapp.com/booking/";

        //JSON object i ile create(post) ettigimiz body'i burada kaydediyoruz
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObject.toString())
                .post(url);

        response.prettyPrint();

        response.then().assertThat()
                                    .statusCode(200)
                                    .contentType("application/json; charset=utf-8")
                                    .body("booking.firstname",Matchers.equalTo("Ali"),
                                            "booking.bookingdates.checkin",Matchers.equalTo("2021-06-01"));

    }

}
