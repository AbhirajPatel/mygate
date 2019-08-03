package com.example.mygate.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mygate.controller.MobileFoodController;
import com.example.mygate.entity.MobileFood;
import com.example.mygate.repository.MobileFoodRepo;
import com.example.mygate.service.FoodService;
import com.example.mygate.util.DataLoader;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MobileFoodControllerTest
{
    @InjectMocks
    private MobileFoodController mobileFoodController;

    @Mock
    private MobileFoodRepo mobileFoodRepo;

    @Mock
    private FoodService mobileFoodService;

    @BeforeClass
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    /**
     *  Scenrios:
     *     When data is there with that applicant name;
     *     When data is not there with that applicant name;
     */
    @Test
    void getMobileFoodsbyApplicantNameTest(){
        MobileFood mobileFood = DataLoader.getMobileFoodObject(DataLoader.getEntity1());
        Mockito.when(mobileFoodRepo.findByApplicant(anyString())).thenReturn(Arrays.asList(mobileFood));
        List<MobileFood> actualList = mobileFoodController.getMobileFoodsbyApplicantName("Applicant");
        Assert.assertFalse(actualList.isEmpty());
        Assert.assertEquals(actualList.get(0), mobileFood);
        Mockito.when(mobileFoodRepo.findByApplicant(anyString())).thenReturn(new ArrayList<>());
        actualList = mobileFoodController.getMobileFoodsbyApplicantName("Applicant123");
        Assert.assertTrue(actualList.isEmpty());
    }

    /**
     * When is expired data in data base;
     * when there no expired data in data base;
     */
    @Test
    void getExpiredMobileFoodTest(){
        MobileFood mobileFood = DataLoader.getMobileFoodObject(DataLoader.getEntity1());
        Mockito.when(mobileFoodService.getExpiredMobileFoodData()).thenReturn(Arrays.asList(mobileFood));
        List<MobileFood> actualList = mobileFoodController.getExpiredMobileFood();
        Assert.assertFalse(actualList.isEmpty());
        Assert.assertEquals(actualList.get(0), mobileFood);
        Mockito.when(mobileFoodService.getExpiredMobileFoodData()).thenReturn(new ArrayList<>());
        actualList = mobileFoodController.getExpiredMobileFood();
        Assert.assertTrue(actualList.isEmpty());
    }

    /**
     * Add mobile food data with ok response;
     * Add mobile food data with 500 response;
     * @throws Exception :
     */
    @Test
    void addMobileFoodTest() throws Exception{
        Map<String, List<Map<String ,Object>>> map = new HashMap<>();
        List<Map<String ,Object>> mapList = new ArrayList<>();
        map.put("mobile_foods",  mapList);
        MobileFood mobileFood = DataLoader.getMobileFoodObject(DataLoader.getEntity1());
        Mockito.when(mobileFoodService.addMobileFood(any())).thenReturn(Arrays.asList(mobileFood));
        ResponseEntity responseEntity = mobileFoodController.addMobileFood(map);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Mockito.when(mobileFoodService.addMobileFood(any())).thenThrow(new Exception());
        responseEntity = mobileFoodController.addMobileFood(map);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Delete when data with given id is there
     * delete when data with given id does not exit;
     */
    @Test
    void deleteMobileFoodTest(){
        MobileFood mobileFood = DataLoader.getMobileFoodObject(DataLoader.getEntity1());
        Mockito.when(mobileFoodService.deleteMobileFood(any())).thenReturn(mobileFood);
        ResponseEntity responseEntity = mobileFoodController.deleteMobileFood(Long.valueOf(1));
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        Mockito.when(mobileFoodService.deleteMobileFood(any())).thenReturn(null);
        responseEntity = mobileFoodController.deleteMobileFood(Long.valueOf(1));
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

    }




}
