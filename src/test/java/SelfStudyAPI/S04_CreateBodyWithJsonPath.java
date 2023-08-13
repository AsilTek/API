package SelfStudyAPI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class S04_CreateBodyWithJsonPath {
    /*
    {
    "firstName": "John",
    "lastName": "doe",
    "age": 26,
    "address": {
        "streetAddress": "naist street",
        "city": "Nara",
        "postalCode": "630-0192"
    },
    "phoneNumbers": [
        {
            "type": "iPhone",
            "number": "0123-4567-8888"
        },
        {
            "type": "home",
            "number": "0123-4567-8910"
        }
    ]
}
     */

    @Test
    public void test01(){

        JSONObject addressInside = new JSONObject();
        addressInside.put("streetAddress", "naist street");
        addressInside.put("city", "Nara");
        addressInside.put("postalCode", "630-0192");

            JSONObject workPhone = new JSONObject();
            workPhone.put("type", "iPhone");
            workPhone.put("number", "0123-4567-8888");

            JSONObject homePhone = new JSONObject();
            homePhone.put("type", "home");
            homePhone.put("number", "0123-4567-8910");

            JSONArray phoneNumbers = new JSONArray();
            phoneNumbers.put(0,workPhone);
            phoneNumbers.put(1,homePhone);

        JSONObject genel = new JSONObject();
        genel.put("firstName", "John");
        genel.put("lastName", "doe");
        genel.put("age", 26);
        genel.put("address",addressInside);
        genel.put("phoneNumbers",phoneNumbers);


        //Yazdirirken getJSONObject kullanarak cagiriyoruz. Assert ile bu okje yazilmaz.
        System.out.println("firstname = "+ genel.get("firstName"));
        System.out.println("address = "+genel.getJSONObject("address").get("streetAddress"));
        System.out.println("work phone = "+genel.getJSONArray("phoneNumbers").getJSONObject(0).get("type"));




    }


}
