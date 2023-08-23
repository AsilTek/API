package SelfStudyAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utilities.SelfStudy_JsonPlaceHolderBaseUrl;
import utilities.SelfStudy_TestData_JsonPlace;

import static io.restassured.RestAssured.given;

public class S13_UsingTestData extends SelfStudy_JsonPlaceHolderBaseUrl {

    /*
    // "https://jsonplaceholder.typicode.com/posts/22" endpointine bit GET request gonderdigimizde
//donen response'un status code 200 oldugunu ve response body nin asagidaki gibi oldugunu test edin
{
        "userId": 3,
        "id": 22,
        "title": "dolor sint quo a velit explicabo quia nam",
        "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void test01(){

        //1- url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",22);

        //2- expected data hazirla
        SelfStudy_TestData_JsonPlace self = new SelfStudy_TestData_JsonPlace();
        JSONObject expData = self.expectedDataCreation13();


        //3- Response object olustur
        Response response = given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");

        //4- Assertion

        JsonPath resBodyJP = response.jsonPath();

        Assert.assertEquals(expData.get("userId"),resBodyJP.get("userId"));
        Assert.assertEquals(expData.get("id"),resBodyJP.get("id"));
        Assert.assertEquals(expData.get("title"),resBodyJP.get("title"));
        Assert.assertEquals(expData.get("body"),resBodyJP.get("body"));

    }
}
