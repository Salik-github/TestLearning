package com.example.restassured.nonBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getreq {
    
    @Test
    public void getAllEmployee()
    {
        RestAssured.baseURI= "http://localhost:3000/";
        RequestSpecification  requestSpecification = RestAssured.given();
        Response  response= requestSpecification.request(Method.GET,"/users/2");
        System.out.println( response.asPrettyString());

    }
    @Test
    public void postOneEmployee()
    {
     RestAssured.baseURI="http://localhost:3000/";
     RequestSpecification requestSpecification = RestAssured.given().header("content-type", "application/json").body(" {\n" + //
                  "        \"name\": \"Salik Smith\",\n" + //
                  "        \"email\": \"jane@example.com\"\n" + //
                  "    }");
    Response response =  requestSpecification.request(Method.POST,"users");
    System.out.println(response.prettyPrint() );
    System.out.println(response.getStatusLine());

    }
    @Test
    public void putEmployee()
    {
        RestAssured.baseURI="http://localhost:3000/";
        RequestSpecification requestSpecification = RestAssured.given().header("context-type","application/json").body("{\n" + //
                        "    \"name\": \"Salik Mohammed \",\n" + //
                        "    \"email\": \"jane@example.com\"\n" + //
                        "  }");
        Response response =  requestSpecification.request(Method.PUT,"users/ceaa");
        System.out.println(response.asPrettyString());

    }
    @Test
    public void deleteEmployee()
    {
        RestAssured.baseURI ="http://localhost:3000/";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.request(Method.DELETE,"users/d704");
        

    }
}
