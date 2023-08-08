package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlSpec {

   protected RequestSpecification specReqresIn;
    protected RequestSpecification specHerokuapp;


    @Before
    public void setUp(){

        specReqresIn = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();

        specHerokuapp = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}
