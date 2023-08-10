package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static utilities.BaseUrl.createBooking;
import static utilities.BaseUrl.createBookingUrl;

public class ApiCalls {

    // We will create dynamic methods for Response
    //This method will return response, we will use Matchers Class


    public static Response checkUserExistWithId(int id, int statusCode, String email, String first_name, String last_name) {

        Response response = given()
                .when()// body goes here
                .get(BaseUrl.reqresInUserId(id));

        response
                .then()
                .statusCode(statusCode)
                .contentType("application/json; charset=utf-8")
                .body("data.email", Matchers.equalTo(email),
                        "data.first_name", Matchers.equalTo(first_name),
                        "data.last_name", Matchers.equalTo(last_name));

        return response;

    }


    public static Response checkUserExistWithIdJsonPath(int id, int statusCode, String email, String first_name, String last_name) {
        Response response = given()
                .when()// body goes here
                .get(BaseUrl.reqresInUserId(id));

        response
                .then()
                .statusCode(statusCode)
                .contentType("application/json; charset=utf-8");


        JsonPath jsonPath = response.jsonPath();

        // junit -> expected, actual
        Assert.assertEquals(email, jsonPath.getString("data.email"));
        Assert.assertEquals(first_name, jsonPath.getString("data.first_name"));
        Assert.assertEquals(last_name, jsonPath.getString("data.last_name"));

        return response;


    }


    public static Response allNamesListReqresIn(int statusCode, String name) {

        Response response = given()
                .when()
                .get(BaseUrl.reqresInUsers());

        response
                .then()
                .statusCode(statusCode)
                .contentType("application/json; charset=utf-8");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertTrue(jsonPath.getList("data.first_name").contains(name));

        return response;


    }


    public static Response checkUserWIthIdHerokuapp(int id,
                                                    int statusCode,
                                                    String firstname,
                                                    String lastname,
                                                    int totalPrice,
                                                    boolean depositPaid,
                                                    String checkIn,
                                                    String checkOut) {

        Response response = given()
                .when()
                .get(BaseUrl.herokuappUserId(id));


        response
                .then()
                .assertThat()
                .statusCode(statusCode)
                .contentType("application/json; charset=utf-8")
                .body("firstname", Matchers.equalTo(firstname),
                        "lastname", Matchers.equalTo(lastname),
                        "totalprice", Matchers.equalTo(totalPrice),
                        "depositpaid", Matchers.equalTo(depositPaid),
                        "bookingdates.checkin", Matchers.equalTo(checkIn),
                        "bookingdates.checkout", Matchers.equalTo(checkOut));

        return response;


    }


    public static Response deserializationBooking(int id,
                                                  int statuscode,
                                                  String firstname,
                                                  String lastname,
                                                  double totalprice,
                                                  boolean depositpaid,
                                                  String checkin,
                                                  String checkout) {


        HashMap<String, Object> expectedData = new HashMap<>();

        HashMap<String, Object> bookingdates = new HashMap<>();

        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);

        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);


        Response response = given()
                .when()
                .get(BaseUrl.herokuappUserId(id));

        response.then()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8");

        HashMap<String, Object> actualData = response.as(HashMap.class); // De-serialization is here

        // Assertion

        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("checkin"), actualData.get("checkin"));
        Assert.assertEquals(expectedData.get("checkout"), actualData.get("checkout"));

        return response;
    }


    public static Response serializationBooking(int id,
                                                int statuscode,
                                                String firstname,
                                                String lastname,
                                                int totalprice,
                                                boolean depositpaid,
                                                String checkin,
                                                String checkout) {


        JSONObject expectedData = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);

        Response response = given()
                .when()
                .get(BaseUrl.herokuappUserId(id));

        JSONObject actualData = new JSONObject(response.getBody().asString());

        response.then().statusCode(200);

        Assert.assertEquals(expectedData.getString("firstname"), actualData.getString("firstname"));
        Assert.assertEquals(expectedData.getString("lastname"), actualData.getString("lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"), actualData.getInt("totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"), actualData.getBoolean("depositpaid"));

        JSONObject expectedbookingdates = expectedData.getJSONObject("bookingdates");


        JSONObject actualbookingdates = actualData.getJSONObject("bookingdates");

        Assert.assertEquals(expectedbookingdates.getString("checkin"), actualbookingdates.getString("checkin"));
        Assert.assertEquals(expectedbookingdates.getString("checkout"), actualbookingdates.getString("checkout"));

        return response;
    }

    public static Response createBookingData(int statuscode,String firstname,
                                             String lastname,double totalprice, boolean depositpaid,String additionaneeds,
                                             String checkin, String checkout){

        // we create a dynamic map
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname) ;
        expectedData.put("lastname", lastname) ;
        expectedData.put("totalprice",totalprice) ;
        expectedData.put("depositpaid", depositpaid) ;
        expectedData.put("additionalneeds",additionaneeds ) ;
        expectedData.put("bookingdates", bookingdates) ;
        // We used username and password
        Response response = given()
                .contentType("application/json; Charset=utf-8")
                .auth()
                .basic("admin","password123")
                .body(expectedData.toString())// if we are using JSONObject we should add .toString()
                .when()
                .post(createBookingUrl());
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statuscode);

        // Verify the created data
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("booking.firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("booking.lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"),actualData.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"),actualData.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"),actualData.getString("booking.bookingdates.checkout"));
        return response;
    }


}