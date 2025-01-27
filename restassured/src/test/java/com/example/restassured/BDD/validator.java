package com.example.restassured.BDD;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.ResponseBody;

public class validator {
    
    @Test
    public void getAPI()
    {
        RestAssured.baseURI ="http://localhost:3000/users";
        ResponseBody response =  RestAssured.given().request(Method.GET,"/2").body();
        response.
        System.out.println(response.asPrettyString());
    }
}
