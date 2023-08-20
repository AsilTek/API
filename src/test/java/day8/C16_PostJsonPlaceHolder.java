package day8;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utilities.BaseUrl;
import utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class C16_PostJsonPlaceHolder {

    @Test
    public void postResourceJSONObject() {
    /*
    Request body
     Expected body
     Actual data

Request body

{
"userId":2000,
"title":"Magic Music Man",
"body":"Ulker"
}

Expected Body

{
    "userId": 2000,
    "title": "Magic Music Man",
    "body": "Ulker",
    "id": 101
}



*/

        // 1- Endpoint is in BaseUrl and my requestbody is in TestData
        // 2- Expected data is in TestData
        // 3- Send the request and save the response


        Response response = given()
                .contentType(ContentType.JSON)
                .body(TestData.requestBodyJsonObjectJsonPlaceHolder())
                .when()
                .post(BaseUrl.jsonPlaceHolder());

        response.then()
                .statusCode(201)
                .contentType(ContentType.JSON);


        JSONObject expectedData = TestData.expectedJsonObjectJsonPlaceHolder();


        JsonPath actualData = response.jsonPath();


        // 4- Assertions

        assertEquals(expectedData.getJSONObject("map").getInt("userId"), actualData.getInt("map.userId"));
        assertEquals(expectedData.getJSONObject("map").getString("title"), actualData.getString("map.title"));
        assertEquals(expectedData.getJSONObject("map").getString("body"), actualData.getString("map.body"));


    }


    @Test
    public void postResourceHashMap() {
    /*
    Request body
     Expected body
     Actual data

Request body

{
"userId":2000,
"title":"Magic Music Man",
"body":"Ulker"
}

Expected Body

{
    "userId": 2000,
    "title": "Magic Music Man",
    "body": "Ulker",
    "id": 101
}



*/

        // 1- Endpoint is in BaseUrl and my requestbody is in TestData
        // 2- Expected data is in TestData
        // 3- Send the request and save the response


        Response response = given()
                .contentType(ContentType.JSON)
                .body(TestData.requestBodyHashMapJsonPlaceHolder()) // gson is converting my hashmap to json
                .when()
                .post(BaseUrl.jsonPlaceHolder());

        response.then()
                .statusCode(201)
                .contentType(ContentType.JSON);


        HashMap<String, Object> expectedData = TestData.expectedHashMapJsonPlaceHolder();
        HashMap<String, Object> actualData = response.as(HashMap.class); // De-Serialization


        // 4- Assertions

        assertEquals(expectedData.get("id"), actualData.get("id"));
        HashMap<String, Object> expectedMap = (HashMap) expectedData.get("map");
        assertEquals(expectedMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedMap.get("title"), actualData.get("title"));
        assertEquals(expectedMap.get("body"), actualData.get("body"));


    }

    @Test
    public void updateResource() {


        Response response = given()
                .contentType(ContentType.JSON)
                .body(TestData.updateJsonPlaceHolder())
                .given()
                .put(BaseUrl.jsonPlaceHolder() + "/1");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1),
                        "map.title", equalTo("Magic Music Man"),
                        "map.body", equalTo("Ulker"),
                        "map.userId", equalTo(1));

        response.prettyPrint();


    }


    @Test
    public void patchResource() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(TestData.patchJsonPlaceHolder().toString())
                .given()
                .patch(BaseUrl.jsonPlaceHolder() + "/1");

        response.then()
                .statusCode(200);

        response.prettyPrint();

        String expectedBody = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";
        String expectedTitle = "sun";

        JsonPath actualData = response.jsonPath();

        assertEquals(expectedBody, actualData.getString("body"));
        assertEquals(expectedTitle, actualData.getString("title"));

    }


    @Test
    public void deleteResource() {

        Response response = given()
                .when()
                .delete(BaseUrl.jsonPlaceHolder() + "/1");

        response.then()
                .statusCode(200);

        response.prettyPrint();

    }

}