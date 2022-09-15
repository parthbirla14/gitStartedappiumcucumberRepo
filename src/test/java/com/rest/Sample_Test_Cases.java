package com.rest;
import static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Sample_Test_Cases {
    @Test
    public  void validate_status_code(){
        given()
                .baseUri("https://api.postman.com/")
                .header("x-api-key","PMAK-630739d9090cf40401f7e99a-cb20a4fd37daf707ec4febe070c675984c")

                .when().get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }
    @Test
    public void validate_response_body(){
        given()
                .baseUri("https://api.postman.com/")
                .header("x-api-key","PMAK-630739d9090cf40401f7e99a-cb20a4fd37daf707ec4febe070c675984c").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("Workspace1","Team Workspace","New Personal Workspace","MyWorkSpace","MyWorkSpace"),
        "workspaces.type", hasItems("team","team","personal","personal","personal"),
                        "workspaces[0].name",equalTo("Workspace1"),
                        "workspaces[0].name",is(equalTo("team workspace")),"workspaces.size()", equalTo(3),
                        "workspaces.name", hasItem("Team workspace"));

    }
    @Test
    public  void extract_response(){
   Response res=  given()     //response is interface
                .baseUri("https://api.postman.com/")
                .header("x-api-key","PMAK-630739d9090cf40401f7e99a-cb20a4fd37daf707ec4febe070c675984c")

                .when().get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200).extract().response();
   System.out.println("response= " + res.asString());

    }

    @Test
    public  void extract_single_value_from_response(){
        Response res=  given()     //response is interface
                .baseUri("https://api.postman.com/")
                .header("x-api-key","PMAK-630739d9090cf40401f7e99a-cb20a4fd37daf707ec4febe070c675984c")

                .when().get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200).extract().response();
        System.out.println("workspace name= " + res.path("workspaces[0].name"));

    }



}
