package day3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_getRequest {

    /*
    TEC01: Print the Response that returns when we send a GEt request
    to https://www.reqres.in/api/users/
     */

//1- End-point and request body
    String url = "https://www.reqres.in/api/users/"; //there is no request body

//2- Expected data
    // no need this step

//3- Send our request and save our response
    @Test
    public void printResponse(){

        Response response = given().when().get(url); //this is get request

        response.prettyPrint(); //gives me only JSON response.
        response.prettyPeek(); // gives me everything

        response.then().log().all();
    }

//4- Assertions
     //we have no assertion



}
