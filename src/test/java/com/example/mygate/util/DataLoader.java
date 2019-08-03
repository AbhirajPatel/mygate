package com.example.mygate.util;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.mygate.entity.MobileFood;

public class DataLoader
{
    public static Map<String , Object> getEntity1(){
        Map<String, Object> map = new HashMap<>();
        map.put("locationid", "locationid1");
        map.put("applicant" , "applicant1");
        map.put("facilityType", "facilityType1");
        map.put("cnn", 1231);
        map.put("locationDescription", "locationDescription1");
        map.put("address", "address1");
        map.put("blocklot", "blocklot1");
        map.put("block", "block1");
        map.put("lot", "lot1");
        map.put("permit", "permit1");
        map.put("status", "status1");
        map.put("foodItems", "foodItems1");
        map.put("x", 1231.0);
        map.put("y", 1231.0);
        map.put("latitude", 1231.0);
        map.put("longitude", 1231.0);
        map.put("schedule", "schedule1");
        map.put("nOISent", "nOISent1");
        map.put("approved", "approved1");
        map.put("received", "received1");
        map.put("priorPermit", "priorPermit1");
        map.put("expirationdate", new Date(System.currentTimeMillis() + 30000000));
        map.put("location", "location1");
        return map;
    }
    public static Map<String , Object> getEntity2(){
        Map<String, Object> map = new HashMap<>();
        map.put("locationid", "locationid2");
        map.put("applicant" , "applicant2");
        map.put("facilityType", "facilityType2");
        map.put("cnn", 1232);
        map.put("locationDescription", "locationDescription2");
        map.put("address", "address2");
        map.put("blocklot", "blocklot2");
        map.put("block", "block2");
        map.put("lot", "lot2");
        map.put("permit", "permit2");
        map.put("status", "status2");
        map.put("foodItems", "foodItems2");
        map.put("x", 1232.0);
        map.put("y", 1232.0);
        map.put("latitude", 1232.0);
        map.put("longitude", 1232.0);
        map.put("schedule", "schedule2");
        map.put("nOISent", "nOISent2");
        map.put("approved", "approved2");
        map.put("received", "received2");
        map.put("priorPermit", "priorPermit2");
        map.put("expirationdate", new Date(System.currentTimeMillis() + 20000000));
        map.put("location", "location2");
        return map;
    }
    public static Map<String , Object> getEntity3(){
        Map<String, Object> map = new HashMap<>();
        map.put("locationid", "locationid3");
        map.put("applicant" , "applicant3");
        map.put("facilityType", "facilityType3");
        map.put("cnn", 1233);
        map.put("locationDescription", "locationDescription3");
        map.put("address", "address3");
        map.put("blocklot", "blocklot3");
        map.put("block", "block3");
        map.put("lot", "lot3");
        map.put("permit", "permit3");
        map.put("status", "status3");
        map.put("foodItems", "foodItems3");
        map.put("x", 1233.0);
        map.put("y", 1233.0);
        map.put("latitude", 1233.0);
        map.put("longitude", 1233.0);
        map.put("schedule", "schedule3");
        map.put("nOISent", "nOISent3");
        map.put("approved", "approved3");
        map.put("received", "received3");
        map.put("priorPermit", "priorPermit3");
        map.put("expirationdate", new Date(System.currentTimeMillis() + 10000000));
        map.put("location", "location3");
        return map;
    }

    public static MobileFood getMobileFoodObject(Map<String, Object> map){
        MobileFood mobileFood = null;
        try{
            mobileFood = MobileFoodBuilder.getObjectFromMap(map);
        }catch (Exception e){
            //do nothing;
        }
        return mobileFood;
    }
}
