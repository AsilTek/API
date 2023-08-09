package day5;

import org.junit.Test;
import utilities.ApiCalls;

import static utilities.ApiCalls.*;

public class C10_getRequest {

    @Test
    public void id5ExistInReqresIn(){

        ApiCalls.checkUserExistWithId(5,200,"charles.morris@reqres.in","Charles","Morris");
    }

    @Test
    public void id2ExistInReqresIn(){

        ApiCalls.checkUserExistWithId(2,200,"janet.weaver@reqres.in","Janet","Weaver");
    }

    @Test
    public void nameExistReqresIn(){

        allNamesListReqresIn(200,"Emma");

    }

    @Test
    public void id8ExitstInReqresIn(){

        checkUserExistWithIdJsonPath(10,200, "byron.fields@reqres.in","Byron","Fields");

    }

    @Test
    public void idHerokuappId() {

        checkUserWIthIdHerokuapp(510, 200, "Josh", "Allen", 111, true, "2018-01-01", "2019-01-01");
    }
}

