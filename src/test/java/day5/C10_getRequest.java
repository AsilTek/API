package day5;

import org.junit.Test;
import utilities.ApiCalls;

import static utilities.ApiCalls.allNamesListReqresIn;

public class C10_getRequest {

    @Test
    public void id5ExistInReqresIn(){

        ApiCalls.checkUserExistsWithId(5,200,"charles.morris@reqres.in","Charles","Morris");
    }

    @Test
    public void id2ExistInReqresIn(){

        ApiCalls.checkUserExistsWithId(2,200,"janet.weaver@reqres.in","Janet","Weaver");
    }

    @Test
    public void nameExistReqresIn(){

        allNamesListReqresIn(200,"Emma");

    }
}

