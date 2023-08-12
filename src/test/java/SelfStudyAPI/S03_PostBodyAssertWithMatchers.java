package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;;
import org.json.JSONObject;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class S03_PostBodyAssertWithMatchers {

    @Test
    public void test01(){

        //Endpoint
        String url = "https://jsonplaceholder.typicode.com/posts";

        //JSONObject olusturarak body'i olusturuyoruz
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title","API");
        jsonObject.put("body","API ogrenmek ne guzel");
        jsonObject.put("userId",10);

        //response objesi olusturarak response'u buraya kaydediyoruz.
        //ve hazirladigimiz body'i gonderiyoruz.
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObject.toString()) //her ne kadar body JSON ile olusturulmus olsa da,java ile calisiyor.
                .post(url);

        response.prettyPrint();

        //Dogrulugunu kanitlamamiz icin verilen bilgileri assertThat ile ve
        // Matchers methodu yardimiyla verify yapiyoruz.
        response.then().assertThat()
                .statusCode(201)
                .contentType("application/json")
                .body("title", equalTo("API")) //Matchers importu ile yazmaya gerek kalmadi.
                .body("userId",lessThan(100))
                .body("body",containsString("API"));
    }
}
