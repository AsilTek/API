package utilities;

import org.json.JSONObject;

import java.util.HashMap;

public class TestData {

    public static HashMap<String, Object> getBookingData(){

        /*
        {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
         */

        HashMap<String, Object> expectedData = new HashMap<>();
        HashMap<String, Object> bookingdates = new HashMap<>();

        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout","2019-01-01");

        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111.0);
        expectedData.put("depositpaid",true);
        expectedData.put("additionalneeds","Breakfast");
        expectedData.put("bookingdates",bookingdates);

        return expectedData;

    }

    public static JSONObject getBookingDataJSON() {

        JSONObject expectedData = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checout","2019-01-01");

        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("additionalneeds","Breakfast");
        expectedData.put("bookingdates",bookingdates);

        return expectedData;
    }
}
