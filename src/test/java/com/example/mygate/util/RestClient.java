package com.example.mygate.util;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    public static Response get(String hostUrl, String uri, Map<String, ?> queryParams){
        Response response;
        if ( queryParams == null )
        {

            response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                    .queryParameters(new HashMap<>()).when().get(hostUrl + uri).then().extract().response();
        }
        else
        {
            response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                    .queryParams(queryParams).when().get(hostUrl + uri).then().extract().response();
        }
        return response;
    }
    public static Response getWithBody(String hostUrl, String uri, Map<String, ?> body){
        Response response;

            response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                    .body(body).when().get(hostUrl + uri).then().extract().response();

        return response;
    }
    public static Response get(String hostUrl, String uri){
        LOGGER.info(hostUrl + uri);
           return  given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                    .queryParameters(new HashMap<>()).when().get(hostUrl + uri).then().extract().response();
    }

    public static Response get(String hostUrl){
        LOGGER.info(hostUrl);
        return  given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                .queryParameters(new HashMap<>()).when().get(hostUrl).then().extract().response();
    }

    public static Response get(String hostUrl, String uri, String var){
        LOGGER.info(hostUrl + uri);
        return  given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                .queryParameters(new HashMap<>()).when().get(hostUrl + uri + "/" + var).then().extract().response();
    }

    public static Response post(String hostUrl, String uri, Map<String, List<Map<String ,Object>>> map)
    {
        LOGGER.info(hostUrl +  uri);
        Response response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                .body(map).when().post(hostUrl + uri).then().extract().response();
        return response;
    }

    public static Response post(String hostUrl, Map<String, List<Map<String ,Object>>> map)
    {

        LOGGER.info(hostUrl);
        Response response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                .body(map).when().post(hostUrl).then().extract().response();
        return response;
    }

    public static Response post(String hostUrl, String var)
    {

        LOGGER.info(hostUrl);
        Response response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                .when().post(hostUrl + "/" + var).then().extract().response();
        return response;
    }

    public static Response delete(String hostUrl, String var)
    {

        LOGGER.info(hostUrl);
        Response response = given().contentType(ContentType.JSON).relaxedHTTPSValidation()
                .when().delete(hostUrl + "/" + var).then().extract().response();
        return response;
    }

}
