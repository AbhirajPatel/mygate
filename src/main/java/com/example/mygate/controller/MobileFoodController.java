package com.example.mygate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mygate.entity.MobileFood;
import com.example.mygate.repository.MobileFoodRepo;
import com.example.mygate.service.FoodService;
import com.example.mygate.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mygate")
public class MobileFoodController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MobileFoodController.class);
    private static final String MOBILE_FOODS = "mobile_foods";

    @Autowired
    private FoodService foodService;

    @Autowired
    private MobileFoodRepo mobileFoodRepo;


    @GetMapping(path = "/mobilefood/{id}")
    public MobileFood getMobileFood(@PathVariable Long id){
        MobileFood mobileFood = mobileFoodRepo.findById(id).orElse(null);
        return mobileFood;
    }

    /**
     * Find the list of mobileFood enitiy with given applicant name as path variable;
     * @param name : applicant's name
     * @return : List<MobileFood> with given  applicant name;
     */
    @GetMapping(path = "applicant/{name}")
    public List<MobileFood> getMobileFoodsbyApplicantName(@PathVariable String name){
        List<MobileFood> mobileFoods = mobileFoodRepo.findByApplicant(name);
        return mobileFoods;
    }

    /**
     * Find the list of mobileFood enitiy with given street name as path variable;
     * @param name : street name
     * @return : List<MobileFood> with given  street name;
     */
    @GetMapping(path = "street/{name}")
    public List<MobileFood> getMobileFoodsbyStreets(@PathVariable String name){
        List<MobileFood> mobileFoods = mobileFoodRepo.findAllByAddress(name);
        return mobileFoods;
    }

    /**
     * Return all the list whose permit is expired.
     * @return List<MobileFood> : list of expired items
     */
    @GetMapping(path = "expired")
    public List<MobileFood> getExpiredMobileFood(){
        return foodService.getExpiredMobileFoodData();
    }

    /**
     * Add one or more mobile_food to databse;
     * @param map list of mobileFood items
     * @return ResponseEntity
     */
    @PostMapping(path = "addData")
    public ResponseEntity addMobileFood(@RequestBody Map<String, List<Map<String ,Object>>> map)
    {
        List<Map<String, Object>> mobileFoods = map.get(MOBILE_FOODS);
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("MobileFoods", foodService.addMobileFood(mobileFoods));
            LOGGER.info("Sucessfully saved {} MobileFood Entity", mobileFoods.size());
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(e.getMessage());
        }

    }
    /**
     * delete the mobilefood entity with given id;
     * @param id : id of entity to be deleted.
     * @return ResponseEntity
     */
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity deleteMobileFood(@PathVariable Long id)
    {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            MobileFood mobileFood = foodService.deleteMobileFood(id);
            if(mobileFood != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(mobileFood);
            }else{
                LOGGER.info("No MobileFood Enitiy exit with id {}", id);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not found entity with id : " + id);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(e.getMessage());
        }

    }
    /**
     * Get the best truck for the given locations;
     */
    @GetMapping(path = "truck")
    public ResponseEntity getMobileTruckForLocation(@RequestBody Map<String, List<Map<String, Object>>> locations){
        Map<String, Object> responseMap = new HashMap<>();
        List<Map<String, Object>> locationList = locations.get(Constant.LOCATION);
        try{
            MobileFood mobileFood = foodService.getBestTruckForGivenLocation(locationList);
            if(null == mobileFood)
            {
             return ResponseEntity.status(HttpStatus.OK).body("No truck possible for these locations");
            }
            return ResponseEntity.status(HttpStatus.OK).body(mobileFood);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
