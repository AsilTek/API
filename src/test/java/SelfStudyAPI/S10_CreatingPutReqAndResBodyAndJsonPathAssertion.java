package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class S10_CreatingPutReqAndResBodyAndJsonPathAssertion {


    @Test
    public void test01(){

        //end point
        String url = "https://dummy.restapiexample.com/api/v1/update/21";

        //request body
        JSONObject dataInner = new JSONObject();
        dataInner.put("name","Ahmet");
        dataInner.put("salary","1230");
        dataInner.put("age","44");
        dataInner.put("id",40);

        JSONObject reqBody = new JSONObject();
        reqBody.put("status","success");
        reqBody.put("data",dataInner);

        //respond body exp data
        JSONObject expData = new JSONObject();
        expData.put("status","success");
        expData.put("data",reqBody);
        expData.put("message","Successfully! Record has been updated.");

        // using rest-assured methods, code sets up Put request with a JSON body,sends it to a specified url
        // and saves it into response obj for assertion
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .put(url);

        //respond body yorumlamak icin JsonPath kullaniyoruz
        JsonPath resBodyJP = response.jsonPath();

        //dogrulamak
        SoftAssert softAssert = new SoftAssert();

        //JsonPath uzantili response ile response body(expected data) dogruluyoruz.
        softAssert.assertEquals(resBodyJP.get("data.data.name"),expData.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(resBodyJP.get("data.data.salary"),expData.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(resBodyJP.get("data.data.age"),expData.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(resBodyJP.get("data.data.id"),expData.getJSONObject("data").getJSONObject("data").get("id"));


    }

}
