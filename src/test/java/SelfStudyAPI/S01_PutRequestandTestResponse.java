package SelfStudyAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class S01_PutRequestandTestResponse {

    @Test
    public void put01(){

        //Asagidaki body'i(title-Ahmet...) PUT ile request gonderdigimizde
        // donen Response da en alttaki degerlerle ayni oldugunu test edicez.

        String url = "https://jsonplaceholder.com/posts/70";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title","Ahmet");
        jsonObject.put("body","Merhaba");
        jsonObject.put("userId", 10);
        jsonObject.put("id",70);

    //eger sorgumuzda bi request body gonderiyorsak, gonderdigimiz datanin formatini belirtmek zorundayiz
    //bunu da given() methodundan sonra pre-condition olarak belirlebiliriz.
    //Postmanda yaptigimiz manuel islemde de body gonderirken once raw sonra content type JSON yapiyorduk.
    //Asagida, Gherkin language ile Rest Assured library direkt olarak API calistiriyor.

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObject.toString()) //burada yukarida olusturdugumuz JSON body i gonderiyoruz.
                .put(url);

        // response.prettyPrint(); ile goruntuluyebiliriz.

    //Assertion response temel bilgilerini test ediyoruz.
        response
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");

    }


}
