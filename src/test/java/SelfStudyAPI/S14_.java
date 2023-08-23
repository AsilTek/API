package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utilities.SelfStudy_JsonPlaceHolderBaseUrl;
import utilities.SelfStudy_TestData_JsonPlace;

import static io.restassured.RestAssured.given;

public class S14_ extends SelfStudy_JsonPlaceHolderBaseUrl {

    @Test
    public void test01(){

//1- url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2","70");

//2-Expected Data hazirla
        SelfStudy_TestData_JsonPlace self = new SelfStudy_TestData_JsonPlace();
        JSONObject expData = self.expectedDataCreation14();

//3- Request Body hazirla
        JSONObject reqBody = self.requestBodyCreation14();

//4- Response object olustur
        Response response = given()
                                    .spec(specJsonPlace)
                                    .contentType(ContentType.JSON)
                                    .when()
                                    .body(reqBody.toString())
                                    .put("/{pp1}/{pp2}");
//5- Assertion
       // response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8");

        JsonPath resBodyJP = response.jsonPath();

        Assert.assertEquals(self.statusCode,response.getStatusCode());
        Assert.assertEquals(self.contentType,response.getContentType());
        Assert.assertEquals(self.header,response.getHeader("Connection"));
        Assert.assertEquals(expData.get("title"),resBodyJP.get("title"));
        Assert.assertEquals(expData.get("body"),resBodyJP.get("body"));
        Assert.assertEquals(expData.get("userId"),resBodyJP.get("userId"));
        Assert.assertEquals(expData.get("id"),resBodyJP.get("id"));
    }
}
