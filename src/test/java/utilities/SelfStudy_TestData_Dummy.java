package utilities;

import java.util.HashMap;

public class SelfStudy_TestData_Dummy {

    public int statusCodeDummy = 200;
    public String contentType = "application/json";

    public HashMap dataBodyCreation(){

        HashMap<String,Object> dataInside = new HashMap<>();
        dataInside.put("id",3.0);
        dataInside.put("employee_name","Ashton Cox");
        dataInside.put("employee_salary",86000.0);
        dataInside.put("employee_age",66.0);
        dataInside.put("profile_image","");

        return dataInside;
    }
    public HashMap expectedBodyMapCreation(){

        HashMap<String,Object> expBody = new HashMap<>();
        expBody.put("status","success");
        expBody.put("data",dataBodyCreation());
        expBody.put("message","Successfully! Record has been fetched.");

        return expBody;
    }
}
