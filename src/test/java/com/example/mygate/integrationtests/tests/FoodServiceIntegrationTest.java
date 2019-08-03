package com.example.mygate.integrationtests.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mygate.util.Constant;
import com.example.mygate.util.DataLoader;
import com.example.mygate.util.FoodServiceUtility;
import com.jayway.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This will test the different apis;
 * To run this test make sure the spring boot application is running in 8080 port only;
 * Host url used here is - http://localhost:8080
 * Go to proper directroy and rung "mvn spring-boot:run" to start the application.
 * Application running is depended on the dockerised postgres database -- refer to readme file of app;
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FoodServiceIntegrationTest
{
    public static List<Map<String, Object>> mapList;

    private static List<Long> ids;

    /**
     *  Set the values to data bases totest;
     *  Set the enviroment to be cleaned up after the test;
     */
    @BeforeClass
    void setup(){
        mapList = new ArrayList<>();
        ids = new ArrayList<>();
        mapList.add(DataLoader.getEntity1());
        Map<String, List<Map<String ,Object>>> map = new HashMap<>();
        map.put("mobile_foods", mapList);
        Response response = FoodServiceUtility.post("addData", map);
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Integer> mobileFoodList = response.jsonPath().getList("MobileFoods.id");
        ids.add(Long.valueOf(mobileFoodList.get(0)));

    }

    /**
     *  Scenerio :
     *  Get the all Mobilefood data with expired certificates;
     */
    @Test
    void getExpiredMobileFood(){
        Response response = FoodServiceUtility.get("expired");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /**
     * Add the list of Mobile food;
     * Also we are testing the delete api as well.
     */
    @Test
    void addMobileFood(){
            Map<String, List<Map<String, Object>>> map = new HashMap<>();
            List<Map<String, Object>> mapLists = new ArrayList<>();
            mapLists.add(DataLoader.getEntity2());
            mapLists.add(DataLoader.getEntity3());
            map.put("mobile_foods", mapLists);
            Response response = FoodServiceUtility.post("addData", map);
            Assert.assertEquals(response.getStatusCode(), 200);
            String res = response.jsonPath().prettify();
            List<Integer> mobileFoodList = response.jsonPath().getList("MobileFoods.id");
            //the saved value will contains id;
            Assert.assertEquals(mobileFoodList.size(), 2);
            for (int mobileFood : mobileFoodList)
            {
                ids.add(Long.valueOf(mobileFood));
                response = FoodServiceUtility.get("mobilefood", String.valueOf(mobileFood));
                Assert.assertEquals(response.getStatusCode(), 200);
            }
    }

    /**
     * Api testing;
     * Get all mobile food with applicant name
     */
    @Test
    void getMobileFoodWithApplicantName(){
            String applicantName = DataLoader.getEntity1().get("applicant").toString();
            Response response = FoodServiceUtility.get("applicant", applicantName);
            Assert.assertEquals(response.getStatusCode(), 200);
            Map<String, Object> mobileFood = (Map<String, Object>) response.jsonPath().getList("$").get(0);
            Assert.assertEquals(mobileFood.get("applicant"), applicantName);
            Assert.assertEquals(mobileFood.get("address"), DataLoader.getEntity1().get("address").toString());
    }

    /**
     * Api -- to get all mobile food with street name;
     */
    @Test
    void getMobileFoodWithStreetName(){
        String streetName = DataLoader.getEntity1().get("address").toString();
        Response response = FoodServiceUtility.get("street", streetName);
        Assert.assertEquals(response.getStatusCode(), 200);
        Map<String, Object> mobileFood =  (Map<String, Object>)response.jsonPath().getList("$").get(0);
        Assert.assertEquals(mobileFood.get("address"), streetName);
    }

    /**
     * Find the best truck for the given location;
     */
    @Test
    void getBestTruck(){
        List<Map<String,Object>> locationList = new ArrayList<>();
        Map<String, Object> location1 = new HashMap<>();
        location1.put(Constant.LONGITUDE, 1235);
        location1.put(Constant.LATITUDE, 1242.23);
        locationList.add(location1);
        Map<String, List<Map<String, Object>>> querryParam = new HashMap<>();
        querryParam.put(Constant.LOCATION, locationList);
        Response response = FoodServiceUtility.get("truck", querryParam);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /**
     * clean up the database created while testing;
     * calling delete and point as well;
     */
    @AfterClass
    void cleanUp(){
        for(Long id : ids){
            FoodServiceUtility.delete("delete", id.toString());
        }
    }
}
