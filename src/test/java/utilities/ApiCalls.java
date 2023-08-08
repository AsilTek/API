package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiCalls {

    //We will create dynamic methods for response
    //This method will return response, we will use Matchers Class

    public static Response checkUserExistsWithId(int id, int statuscode, String email, String first_name, String last_name){

        Response response = given()
                .when() //body goes in here
                .get(BaseUrl.reqresInUserId(id));

        response.then()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8")
                .body("data.email", Matchers.equalTo(email),
                        "data.first_name",Matchers.equalTo(first_name),
                        "data.last_name",Matchers.equalTo(last_name));

        return response;
    }

    public static Response allNamesListReqresIn(int statusCode, String name){

        Response response = given()
                .when() //body goes in here
                .get(BaseUrl.reqresInUsers());

        response.then()
                .statusCode(statusCode)
                .contentType("application/json; charset=utf-8");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertTrue(jsonPath.getList("data.first_name").contains(name));

        return response;



    }



}
