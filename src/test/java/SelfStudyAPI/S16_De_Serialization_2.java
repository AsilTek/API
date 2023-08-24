package SelfStudyAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.SelfStudy_DummyRestApiBaseUrl;
import utilities.SelfStudy_TestData_Dummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.path.json.JsonPath.given;

public class S16_De_Serialization_2 extends SelfStudy_DummyRestApiBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/employee/3 e Get request gonder.Donen response da status =200
    contentType application/json oldugunu test et(exp data
     */

    @Test
    public void test01(){

        //1- url hazirla
        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4","3");

        //2- Expected Data
        SelfStudy_TestData_Dummy testDataDummy = new SelfStudy_TestData_Dummy();
        HashMap<String,Object> expData = testDataDummy.expectedBodyMapCreation();

        //3- Response kaydet
        Response response = RestAssured.given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");

        //4- Assertion --Java map icindeki map'i cast etmeden ulasmaya izin vermiyor.

        HashMap<String,Object> responseBody = response.as(HashMap.class);

        Assert.assertEquals( ((Map)(expData.get("data"))).get("id"), ((Map)(responseBody.get("data"))).get("id"));
        Assert.assertEquals( ((Map)(expData.get("data"))).get("employee_name"), ((Map)(responseBody.get("data"))).get("employee_name"));
        Assert.assertEquals( ((Map)(expData.get("data"))).get("employee_age"), ((Map)(responseBody.get("data"))).get("employee_age"));
    }
}
