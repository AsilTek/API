package SelfStudyAPI;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class S06_GetRequestWithJsonPath {

    @Test
    public void test01(){

        String url = "http://dummy.restapiexample.com/api/v1/employees";


        Response response = given().when().get(url);

        response.prettyPrint();

        //gelen listede 24 kisi olup olmadigini,Ashton Cox diye birinin olup olmadigini,
        //calisanlarda 61,30,40 yaslarinda birileri olup olmadigini test edin.
        response.then().assertThat()
                                    .statusCode(200)
                                    .contentType("application/json")
                                    .body("data.id", hasSize(24),
                                            "data.employee_name",hasItem("Ashton Cox"),
                                            "data.employee_age",hasItems(61,30,40));
    }
}
