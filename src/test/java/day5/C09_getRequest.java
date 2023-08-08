package day5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;
import static utilities.BaseUrl.reqresInUsers;

public class C09_getRequest  {

    //1- End point and request body
    //My end point is in BaseUrl class -no request body

    //2- I am not preparing an expecte data

    //3- Send request and save response

    Response response = given()
                                .when()
                                .get(reqresInUsers());


    JsonPath jsonPath = response.jsonPath();

    @Test
    public void nameExistingRegresInUsers(){
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
        System.out.println(jsonPath.getList("data.first_name"));


    }

    @Test
    public void emailExistsInReqresIn(){

        response.then()
                .assertThat()
                .statusCode(200);

        Assert.assertTrue(jsonPath.getList("data.email").contains("eve.holt@reqres.in"));

    }

    @Test
    public void supportExistsReqresInText(){

        response.then()
                .assertThat()
                .statusCode(200);

        Assert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",jsonPath.getString("support.text"));
    }

}
