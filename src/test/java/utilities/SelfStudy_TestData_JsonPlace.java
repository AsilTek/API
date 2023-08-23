package utilities;

import org.json.JSONObject;

public class SelfStudy_TestData_JsonPlace {

    public int statusCode = 200;
    public String contentType = "application/json; charset=utf-8";
    public String header = "keep-alive";

    //S13 icin
    public JSONObject expectedDataCreation13(){

        JSONObject expData = new JSONObject();
        expData.put("userId",3);
        expData.put("id",22);
        expData.put("title","dolor sint quo a velit explicabo quia nam");
        expData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return expData;
    }

    //S14 icin exp data
    public JSONObject expectedDataCreation14(){

        JSONObject expData = new JSONObject();
        expData.put("title","Ahmet");
        expData.put("body","Merhaba");
        expData.put("userId",10);
        expData.put("id",70);

        return expData;
    }

    //S14 icin request body
    public JSONObject requestBodyCreation14(){

        JSONObject reqBody = new JSONObject();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);

        return reqBody;
    }



}
