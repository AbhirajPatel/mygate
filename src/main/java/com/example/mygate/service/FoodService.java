package com.example.mygate.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.mygate.entity.MobileFood;
import com.example.mygate.repository.MobileFoodRepo;
import com.example.mygate.util.MobileFoodBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mygate.exception.MyGateInvalidDataException;

@Service
public class FoodService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    private MobileFoodRepo mobileFoodRepo;

    @Autowired
    private AssignTruckService assignTruckService;

    /**
     * get the list expired mobile food data;
     * @return :
     */
    public List<MobileFood> getExpiredMobileFoodData(){
        Date today = new Date(new java.util.Date().getTime());
        return mobileFoodRepo.findExpiredMobileDate(today);
    }

    /**
     * Add list of the data to database; if any will not be saved then throw error
     * @param map : map
     * @return : List<MobileFood> that are saved to databases;
     * @throws Exception :
     */
    public List<MobileFood> addMobileFood(List<Map<String, Object>> map) throws Exception{
        List<MobileFood> mobileFoodList = new LinkedList<>();
        try
        {
            for (Map<String, Object> mobileFoodMap : map)
            {
                MobileFood mobileFood = MobileFoodBuilder.getObjectFromMap(mobileFoodMap);
                mobileFoodList.add(mobileFood);
            }
        }catch (Exception e){
            LOGGER.error("Could not save the list data base with following error : {}", e.getMessage());
            throw new MyGateInvalidDataException("Data to save is not valid : " + e.getMessage());
        }
        if(!mobileFoodList.isEmpty())
        {
            mobileFoodRepo.saveAll(mobileFoodList);
            LOGGER.info("List is successful saved to database");
        }else {
            LOGGER.debug("Given map to save is empty");
        }
        return mobileFoodList;
    }

    /**
     * Delete the Entity with given Id;
     * @param id : id
     * @return : Mobile Food Entity;
     */
    public MobileFood deleteMobileFood(Long id){
        MobileFood mobileFood = mobileFoodRepo.findById(id).orElse(null);
        if(null != mobileFood){
            mobileFoodRepo.delete(mobileFood);
            LOGGER.info("Mobile Food entity with id {} is deleted Successfully", id);
        }
        return mobileFood;
    }

    /**
     * Get the trcuk suitable best for the given list of the location;
     * @param locationList :
     * @return : MobileFood
     * @throws :
     */
    public MobileFood getBestTruckForGivenLocation(List<Map<String, Object>> locationList)
            throws MyGateInvalidDataException{
          assignTruckService.validateLocationList(locationList);
         return assignTruckService.bestTruckForGivenLocation(locationList);
    }
}
