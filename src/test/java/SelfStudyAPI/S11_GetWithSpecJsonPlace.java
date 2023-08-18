package SelfStudyAPI;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.SelfStudy_JsonPlaceHolderBaseUrl;

import static io.restassured.RestAssured.given;

public class S11_GetWithSpecJsonPlace extends SelfStudy_JsonPlaceHolderBaseUrl {


    @Test
    public void testGet01(){
// "https://jsonplaceholder.typicode.com/posts" endpointine bit GET request gonderdigimizde
//donen response'un status code 200 olduugnu ve Response da 100 kayit oldugunu test edin.

//1- base olan kismi zaten BaseUrl de hazirladik, onu cagiriyoruz ve path leri ekliyoruz
    specJsonPlace.pathParam("pp1","posts");

//2- Expected data hazirla

//3- Response kaydet...farkli olarak objeyi given'dan sonra koyup
//   get icine url degil son ekledigimiz kismin key value'sunu yaziyoruz
        Response response = given()
                                    .spec(specJsonPlace)
                                    .when()
                                    .get("/{pp1}");

        response.prettyPrint();

//4- assertion
        response.then().assertThat()
                                    .statusCode(200)
                                    .body("title", Matchers.hasSize(100));

    }

    @Test
    public void testGet02(){
// "https://jsonplaceholder.typicode.com/posts/44" endpointine bit GET request gonderdigimizde
//donen response'un status code 200 ve title degerininin "optio dolor molestias sit" oldugunu test edin.

        specJsonPlace.pathParams("pp1","posts","pp2", 44);

        Response response = given().spec(specJsonPlace)
                                                        .when()
                                                                .get("/{pp1}/{pp2}");

        response.then().assertThat()
                                    .statusCode(200)
                                    .body("title",Matchers.equalTo("optio dolor molestias sit"));
    }

    @Test
    public void testDelete01(){
// "https://jsonplaceholder.typicode.com/posts/50" endpointine bit DELETE request gonderdigimizde
//donen response'un status code 200 ve response body'nin null oldugunu test edin.

        specJsonPlace.pathParams("pp1","posts","pp2",50);

        Response response = given().spec(specJsonPlace)
                                                        .when()
                                                        .delete("/{pp1}/{pp2}");

        response.then().assertThat().statusCode(200).body("title",Matchers.nullValue());

    }
}
