package SelfStudyAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Pojo_JsonPlaceRequestBody;
import utilities.SelfStudy_JsonPlaceHolderBaseUrl;

import static io.restassured.path.xml.XmlPath.given;

public class S17_ extends SelfStudy_JsonPlaceHolderBaseUrl {
    /*
    // "https://jsonplaceholder.typicode.com/posts/70" endpointine asagidaki body e sahip PUT request gonderdigimizde
//donen response'un  response body nin asagidaki gibi oldugunu test edin (ikiside ayni body)
{
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
    }
     */
    @Test
    public void test01(){

        //1- url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2","70");

        //2- Request body hazirla
        Pojo_JsonPlaceRequestBody reqBody = new Pojo_JsonPlaceRequestBody("Ahmet","Merhaba",10,70);

        System.out.println(reqBody);

        //3- Expected data hazirla (reqBody ile ayni oldugu icin)
        Pojo_JsonPlaceRequestBody expData = new Pojo_JsonPlaceRequestBody("Ahmet","Merhaba",10,70);

        //4- Response kaydet
        Response response = RestAssured.given()
                                                .spec(specJsonPlace)
                                                .contentType(ContentType.JSON)
                                                .when()
                                                .body(reqBody)
                                                .put("/{pp1}/{pp2}");
        response.prettyPrint();

        //5-Assertion
        Pojo_JsonPlaceRequestBody respPJ = response.as(Pojo_JsonPlaceRequestBody.class);

        Assert.assertEquals(expData.getTitle(),respPJ.getTitle());
        Assert.assertEquals(expData.getBody(),respPJ.getBody());
        Assert.assertEquals(expData.getId(),respPJ.getId());
        Assert.assertEquals(expData.getUserId(),respPJ.getUserId());
    }















}
