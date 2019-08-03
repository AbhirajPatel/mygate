package com.example.mygate.util;

import java.util.HashSet;
import java.util.Set;

public class Constant
{
    /**
     * No instance of this class;
     */
    private Constant(){}

    public static final String ID = "id";
    public static final String LOCATION_ID = "locationid";
    public static final String APPLICANT = "applicant";
    public static final String FACILITY_TYPE = "facilityType";
    public static final String CNN = "cnn";
    public static final String LOCATION_DESC = "locationDescription";
    public static final String ADDRESS = "address";
    public static final String BLOCKLOT = "blocklot";
    public static final String BLOCK = "block";
    public static final String LOT = "lot";
    public static final String PERMIT = "permit";
    public static final String STATUS = "status";
    public static final String FOOD_ITEM = "foodItems";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String SCHEDULE = "schedule";
    public static final String NOISENT ="nOISent";
    public static final String APPROVED = "approved";
    public static final String RECEIVED = "received";
    public static final String PRIOR_PERMIT = "priorPermit";
    public static final String EXPIRATION_DATE = "expirationdate";
    public static final String LOCATION = "location";

    /**
     * Return sets of all constants;
     */
    public static Set<String> getAllConstants(){
        Set<String> constants = new HashSet<>();
        constants.add(Constant.ID);
        constants.add(Constant.PRIOR_PERMIT);
        constants.add(Constant.NOISENT);
        constants.add(Constant.RECEIVED);
        constants.add(Constant.SCHEDULE);
        constants.add(Constant.STATUS);
        constants.add(Constant.PERMIT);
        constants.add(Constant.LOCATION_ID);
        constants.add(Constant.LOCATION_DESC);
        constants.add(Constant.LOCATION);
        constants.add(Constant.LOT);
        constants.add(Constant.LONGITUDE);
        constants.add(Constant.LATITUDE);
        constants.add( Constant.Y);
        constants.add(Constant.X);
        constants.add(Constant.FOOD_ITEM);
        constants.add(Constant.FACILITY_TYPE);
        constants.add(Constant.EXPIRATION_DATE);
        constants.add(Constant.CNN);
        constants.add(Constant.BLOCK);
        constants.add(Constant.BLOCKLOT);
        constants.add(Constant.APPROVED);
        constants.add(Constant.APPLICANT);
        constants.add(Constant.ADDRESS);
        return constants;
    }



}
