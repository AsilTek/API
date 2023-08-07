package day3;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_getRequest {

    /*
    ***  TC03:Send a GET request to https://reqres.in/api/users/2
     and verify:
     The status code is 200 ;
     The content type is application/json; Charset=utf-8
     The value of Header named Server is cloudflare
     The status line is HTTP/1.1 200 ok
     */

    //1- End-point and request body
    String url = "https://reqres.in/api/users/2";

    Response response = given().when().get(url);

//2- Expected data
    // no need this step

    //3- Send our request and save our response
    @Test
    public void getHerokuAppBooking10(){

        //Response response = given().when().get(url);

        System.out.println("Status code is: "+response.statusCode());
        System.out.println("Content type is: "+response.contentType());
        System.out.println("Server name is: "+response.header("Server"));
        System.out.println("Status line is: "+response.statusLine());
    }
    //4- Assertions
    @Test
    public void herokuAppAssert(){
        response.then().assertThat().statusCode(200).contentType("application/json; Charset=utf-8").header("Server","cloudflare").statusLine("HTTP/1.1 200 OK");

    }

    @Test
    public void herokuAppJunitAssert(){

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        Assert.assertEquals("cloudflare",response.header("Server"));
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());

    }

}

