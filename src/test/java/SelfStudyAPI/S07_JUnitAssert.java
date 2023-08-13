package SelfStudyAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class S07_JUnitAssert {

    @Test
    public void test01(){

        String url = "https://jsonplaceholder.typicode.com/posts/22";

        JSONObject expData = new JSONObject();
        expData.put("userId",3);
        expData.put("id",22);
        expData.put("title","dolor sint quo a velit explicabo quia nam");
        expData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");
        System.out.println(expData);


        Response response = given().when().get(url);
        response.prettyPrint();

        JsonPath responseJP = response.jsonPath(); //gelen responsu yorumlamak icin JsonPath e cevirmek zorundayiz

        //EqualTo da direk value yaziyoduk. Get ile key yaziyoruz value geliyor
        Assert.assertEquals(expData.get("userId"),responseJP.get("userId"));
        Assert.assertEquals(expData.get("id"),responseJP.get("id"));
        Assert.assertEquals(expData.get("title"),responseJP.get("title"));




    }



}
