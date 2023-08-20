package day8;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utilities.BaseUrl;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C15_JsonPlaceHolder {


    /*

    Get all resources

    Get one resource

    Post one resource

    Put one resource

    Patch one resource

    Delete one resource

   Serialization, De-Serialization, BaseUrl, Spec,

         // 1- End-point and request body
        // 2- Expected data
        // 3- Send request and save response (response, jsonpath, jsonObject, HashMap, ?)
        // 4- Assertions
     */

    @Test
    public void getAllResources() {

        Response response = given()
                .when()
                .get(BaseUrl.jsonPlaceHolder());

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all();


    }


    @Test
    public void getOneResource(){


        // expected Data with HashMap

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("id",1);
        expectedData.put("userId",1);
        expectedData.put("title","sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expectedData.put("body","quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");




        Response response =given()
                .when()
                .get(BaseUrl.jsonPlaceHolder()+"/1");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        // .log().all();

        response.prettyPrint();

        // JsonPath
        JsonPath actualData = response.jsonPath();

        assertEquals(expectedData.get("id"),actualData.getInt("id"));
        assertEquals(expectedData.get("userId"),actualData.getInt("userId"));
        assertEquals(expectedData.get("title"),actualData.getString("title"));
        assertEquals(expectedData.get("body"),actualData.getString("body"));





    }

    @Test
    public void getOneResourceDeSerialization(){


        // expected Data with HashMap

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("id",1.0);
        expectedData.put("userId",1.0);
        expectedData.put("title","sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expectedData.put("body","quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");




        Response response =given()
                .when()
                .get(BaseUrl.jsonPlaceHolder()+"/1");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        // .log().all();

        // HashMap de-serialization
        // that the json response and convert to java -> HashMap

        HashMap<String,Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("body"),actualData.get("body"));



    }


    @Test
    public void getOneResourceJSONObject(){


        // expected Data with JSONObject

        JSONObject expectedData = new JSONObject();

        expectedData.put("id",1);
        expectedData.put("userId",1);
        expectedData.put("title","sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expectedData.put("body","quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");


        Response response =given()
                .when()
                .get(BaseUrl.jsonPlaceHolder()+"/1");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        // .log().all();

        // JsonPath
        JsonPath actualData = response.jsonPath();

        assertEquals(expectedData.get("id"),actualData.getInt("id"));
        assertEquals(expectedData.get("userId"),actualData.getInt("userId"));
        assertEquals(expectedData.get("title"),actualData.getString("title"));
        assertEquals(expectedData.get("body"),actualData.getString("body"));





    }


    @Test
    public void getOneResourceJSONObjectActualDataJSONObject(){


        // expected Data with JSONObject

        JSONObject expectedData = new JSONObject();

        expectedData.put("id",1);
        expectedData.put("userId",1);
        expectedData.put("title","sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expectedData.put("body","quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");


        Response response =given()
                .when()
                .get(BaseUrl.jsonPlaceHolder()+"/1");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        // .log().all();

        // Convert response to JSONObject

        JSONObject actualData = new JSONObject(response.getBody().asString());


        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("body"),actualData.get("body"));


// BREAK TILL 8:17 PM


    }

}