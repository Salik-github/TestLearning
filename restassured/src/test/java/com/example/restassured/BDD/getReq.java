package com.example.restassured.BDD;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class getReq {
    
    @Test
    public void getEmployee()
    {
        RestAssured.baseURI ="http://localhost:3000/";
        Response response = RestAssured.given().request(Method.GET,"users");
    }
}
