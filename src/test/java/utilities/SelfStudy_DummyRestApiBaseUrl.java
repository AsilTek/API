package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class SelfStudy_DummyRestApiBaseUrl {

    protected RequestSpecification specDummy;

    @Before
    public void setUp(){

        specDummy = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com").build();
    }
}
