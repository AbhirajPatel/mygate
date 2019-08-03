package com.example.mygate.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.mygate.entity.MobileFood;
import com.example.mygate.exception.MyGateInvalidDataException;
import com.example.mygate.repository.MobileFoodRepo;
import com.example.mygate.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignTruckService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AssignTruckService.class);
    private static final int    range  = 100;

    @Autowired
    private MobileFoodRepo mobileFoodRepo;

    /**
     * Get the list of the truck for given location in given range;
     * @param location : location map containing longitude and latitude
     * @return :
     */
    public List<MobileFood> truckListForLocation(Map<String, Object> location){
        Float longitude = Float.parseFloat(location.get(Constant.LONGITUDE).toString());
        Float latitude = Float.parseFloat(location.get(Constant.LATITUDE).toString());
        return  mobileFoodRepo.findMobileFoodLocationRange(longitude - range, longitude+ range,latitude- range,
                latitude+ range);
    }

    /**
     * Validate whether the provided location is valid or not;
     * @param locations :
     * @throws MyGateInvalidDataException : when data is invalid;
     */
    public boolean validateLocationList(List<Map<String, Object>> locations)
        throws MyGateInvalidDataException{
        for(Map<String, Object> location : locations){
            if(location.containsKey("longitude") && location.get("longitude") != null && location.containsKey("latitude")
            && location.get("latitude") != null){
                try{
                    Float.parseFloat(location.get(Constant.LONGITUDE).toString());
                    Float.parseFloat(location.get(Constant.LATITUDE).toString());
                }catch (Exception e){
                    throw new MyGateInvalidDataException("Ivalide location map privided : location map must contains longitude and latitude with value");
                }
            }else{
                throw new MyGateInvalidDataException("Ivalide location map privided : location map must contains longitude and latitude with value");
            }
        }
        return true;
    }

    /**
     * This will return the union of all the truck for all the location.
     * @param locations :
     * @return :
     */
    public Set<MobileFood> truckListForLocation(List<Map<String, Object>> locations){
        Set<MobileFood> mobileFoodSet = new HashSet<>();
        for(Map<String, Object> location : locations)
        {
            List<MobileFood> mobileFoodPerLocation = truckListForLocation(location);
            for(MobileFood mobileFood: mobileFoodPerLocation){
                mobileFoodSet.add(mobileFood);
            }
        }
        return mobileFoodSet;
    }

    /**
     * Get the best truck for the given location that will take least time;
     * If no such truck possible then return null;
     * @param locations :
     * @return :
     */
    public MobileFood bestTruckForGivenLocation(List<Map<String, Object>> locations){
        Set<MobileFood> mobileFoodSet = truckListForLocation(locations);
        MobileFood bestTruck = null;
        float distance = Float.MAX_VALUE;
        for(MobileFood mobileFood : mobileFoodSet){
            if(distance > getDistanceTraveled(mobileFood, locations)){
                bestTruck = mobileFood;
            }
        }
        return bestTruck;
    }

    /**
     * Get the distance traveld to cover all the location;
     * @param mobileFood :
     * @param locations :
     * @return :
     */
    private float getDistanceTraveled(MobileFood mobileFood, List<Map<String, Object>> locations){
        long distance = 0;
        for(Map<String, Object> location : locations){
            float longitude = Float.parseFloat(location.get(Constant.LONGITUDE).toString());
            float latitude = Float.parseFloat(location.get(Constant.LATITUDE).toString());
            distance += Math.sqrt((longitude - mobileFood.getLongitude())*(longitude - mobileFood.getLongitude())
             + (latitude - mobileFood.getLatitude())*(latitude - mobileFood.getLatitude()));
        }
        return distance;
    }

}
