package SelfStudyAPI;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.SelfStudy_HerokuappBaseUrl;

import static io.restassured.RestAssured.given;

public class S12GetWithSpecHerokuapp extends SelfStudy_HerokuappBaseUrl {

    @Test
    public void test01(){

//"https://restful-booker.herokuapp.com/booking" endpointine GET request gonder.
//donen response status code 200 oldugunu ve Response da 33071 bookingid; ye ait bir booking oldugunu test edin

        specHerokuapp.pathParam("pp1","booking");

        Response response =given()
                                    .spec(specHerokuapp)
                                    .when()
                                    .get("/{pp1}");

        response.then().assertThat().body("bookingid", Matchers.hasItem(33071));
    }

    @Test
    public void test02(){
//"https://restful-booker.herokuapp.com/booking" endpointine gerekli Query parametreleri yazarak "firstname" degeri "Eric"
//olan rezervasyonu oldughunu test edecek bir GET request gonderdigimizde, donen response un status code 200 ve Eric ismine
//ait en az bir booking oldugunu test edin.

    specHerokuapp.pathParam("pp1","booking").queryParam("firstname","Eric");

    Response response = given().spec(specHerokuapp).when().get("/{pp1}");

    //postmanda kontrol ettik birden fazla oldugunu gorduk. greaterThan dogru degil cunku bookingid karsisindaki
    //degeri verir kac tane bookingid oldugunu vermez.
    response.then().assertThat().statusCode(200).body("bookingid",Matchers.greaterThan(1));

    }
}
