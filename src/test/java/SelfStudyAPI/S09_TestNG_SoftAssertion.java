package SelfStudyAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class S09_TestNG_SoftAssertion {

    @Test
    public void test01(){

        String url = "https://dummy.restapiexample.com/api/v1/employee/3";

        JSONObject dataInside = new JSONObject();
        dataInside.put("id", 3);
        dataInside.put("employee_name", "Ashton Cox");
        dataInside.put("employee_salary", 86000);
        dataInside.put("employee_age", 66);
        dataInside.put("profile_image", "");

        JSONObject responseBody = new JSONObject();
        responseBody.put("status", "success");
        responseBody.put("data", dataInside);
        responseBody.put("message","Successfully! Record has been fetched");

        Response response = given().when().get(url);

        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        JsonPath resBodyJP = response.jsonPath(); //response body yorumlamak icin

        softAssert.assertEquals(resBodyJP.get("status"),responseBody.get("status"));
        softAssert.assertEquals(resBodyJP.get("message"),responseBody.get("message"));
        softAssert.assertEquals(resBodyJP.get("data.id"),responseBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(resBodyJP.get("employee_name"),responseBody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(resBodyJP.get("employee_salary"),responseBody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(resBodyJP.get("employee_age"),responseBody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(resBodyJP.get("profile_image"),responseBody.getJSONObject("data").get("profile_image"));

        //softAssert.assertAll();

    }

}
