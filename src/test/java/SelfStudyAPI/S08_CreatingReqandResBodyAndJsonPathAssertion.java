package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class S08_CreatingReqandResBodyAndJsonPathAssertion {


    @Test
    public void test01(){

        //end point assign ettik
        String url = "https://www.restful-booker.herokuapp.com/booking";


        //bookingdates icinde obje aldigi icin ona ait baska obje olusturuyoruz
        JSONObject bookingDatesInside = new JSONObject();

        bookingDatesInside.put("checkin","2021-06-01");
        bookingDatesInside.put("checkout","2021-06-10");

        //request body create icin object olusturduk
        JSONObject jsonPathResponse = new JSONObject();

        jsonPathResponse.put("firstname","Ahmet");
        jsonPathResponse.put("lastname","Bulut");
        jsonPathResponse.put("totalprice",500);
        jsonPathResponse.put("depositpaid",false);
        jsonPathResponse.put("bookingdates",bookingDatesInside);
        jsonPathResponse.put("additionalneeds","wi-fi");

        System.out.println(jsonPathResponse); // nasil gorundugune bakmak icin

        //Response body olusturuyoruz Expected data icin
        JSONObject resBody = new JSONObject();
        resBody.put("bookingid",24);
        resBody.put("booking",jsonPathResponse);


        //internet sitesinden gelen response kaydettik ve JSON olarak gonderdik.
        Response response = given()
                                    .contentType(ContentType.JSON)
                                    .when()
                                    .body(jsonPathResponse.toString())
                                    .post(url);

        //Internet sitesinden gelen response yorumlamak icin jsonpath kullandik
        JsonPath jsonPathRes = response.jsonPath();

        //Verify icin dynamic olan JsonPath olusturduk
        Assert.assertEquals(resBody.getJSONObject("booking").get("firstname"),jsonPathRes.get("booking.firstname"));
        Assert.assertEquals(resBody.getJSONObject("booking").get("lastname"),jsonPathRes.get("booking.lastname"));
        Assert.assertEquals(resBody.getJSONObject("booking").get("totalprice"),jsonPathRes.get("booking.totalprice"));
        Assert.assertEquals(resBody.getJSONObject("booking").get("depositpaid"),jsonPathRes.get("booking.depositpaid"));
        Assert.assertEquals(resBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),jsonPathRes.get("booking.bookingdates.checkin"));
        Assert.assertEquals(resBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),jsonPathRes.get("booking.bookingdates.checkout"));
        Assert.assertEquals(resBody.getJSONObject("booking").getJSONObject("additionalneeds"),jsonPathRes.get("booking.additionalneeds"));

    }
}
