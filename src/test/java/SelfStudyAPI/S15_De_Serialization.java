package SelfStudyAPI;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.SelfStudy_JsonPlaceHolderBaseUrl;
import utilities.SelfStudy_TestData_JsonPlace;

import java.util.HashMap;

import static io.restassured.path.json.JsonPath.given;

public class S15_De_Serialization extends SelfStudy_JsonPlaceHolderBaseUrl {

    @Test
    public void test01(){

  //https://jsonplaceholder.typicode.com/post/70 e asagidaki body e sahip bir put request yolladigimida donen responsun
  //response body sinin asagida verilen ile ayni oldugunu test edin.
  /*
  Request body:
  {
  "title" : "Ahmet",
  "body" : "Merhaba",
  "userId" : 10;
  "id" : 70
  }

  Expected data:
         {
  "title" : "Ahmet",
  "body" : "Merhaba",
  "userId" : 10;
  "id" : 70
  }
   */

        //1- url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2","70");

        //2-Request body olustur
        SelfStudy_TestData_JsonPlace testDataJsonPlace = new SelfStudy_TestData_JsonPlace();
        HashMap<String,Object> reqBody = testDataJsonPlace.mapRequestBodyCreation();

        //3- Expected data hazirla -- req body ile response body ayni oldugu icin ayri ayri method olusturmadik
        HashMap<String,Object> expData = testDataJsonPlace.mapRequestBodyCreation();

        //4- Response obje olustur ve kaydet
        Response response = RestAssured.given()
                                                .spec(specJsonPlace)
                                                .contentType(ContentType.JSON)
                                                .when()
                                                .body(reqBody)
                                                .put("/{pp1}/{pp2}");
        response.prettyPrint();

        //5- Assertion //onceden response u test etmek icin JsonPath e ceviriyorduk. Artik HashMap e ceviricez
        HashMap<String,Object> responseMap =response.as(HashMap.class);
        Assert.assertEquals(expData.get("title"),responseMap.get("title"));
        Assert.assertEquals(expData.get("body"),responseMap.get("body"));
        Assert.assertEquals(expData.get("userId"),responseMap.get("userId"));
        Assert.assertEquals(expData.get("id"),responseMap.get("id"));


    }
}
