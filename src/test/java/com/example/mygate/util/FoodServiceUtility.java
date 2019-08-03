package com.example.mygate.util;

import java.util.Map;
import java.util.List;

import com.jayway.restassured.response.Response;


public class FoodServiceUtility
{

    public static final String hostUrl = "http://localhost:8080/mygate/";

   public static Response get(String path){
       return RestClient.get(hostUrl, path);
   }

    public static Response get(String path, String var){
        return RestClient.get(hostUrl, path, var);
    }

    public static Response post(String path, Map<String, List<Map<String ,Object>>> map){
        return RestClient.post(hostUrl + path, map);
    }

    public static Response post(String path, String var){
        return RestClient.post(hostUrl + path, var);
    }
    public static Response delete(String path, String var){
        return RestClient.delete(hostUrl + path, var);
    }

    public static Response get(String path, Map<String, ?> queryParam){
       return RestClient.getWithBody(hostUrl, path, queryParam);
    }
}
