package com.example.mygate.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mygate.exception.MyGateInvalidDataException;
import com.example.mygate.service.AssignTruckService;
import com.example.mygate.util.Constant;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssignTruckServiceTest
{
    @InjectMocks
    private AssignTruckService assignTruckService;

    @BeforeClass
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Scenario :
     *   when location list contains all valid input;
     *   when location list contains invalid inputs as well;
     * @throws Exception :
     */
    @Test
    void validateLocationListTest() throws Exception{
        List<Map<String,Object>> locationList = new ArrayList<>();
        Map<String, Object> location1 = new HashMap<>();
        location1.put(Constant.LONGITUDE, 234.23);
        location1.put(Constant.LATITUDE, 2332.23);
        locationList.add(location1);
        Assert.assertTrue(assignTruckService.validateLocationList(locationList));
        Map<String, Object> invalidLocation = new HashMap<>();
        invalidLocation.put(Constant.LATITUDE, "123");
        invalidLocation.put(Constant.LONGITUDE, "Noting");
        locationList.add(invalidLocation);
        Assert.assertThrows(MyGateInvalidDataException.class, ()->{
            assignTruckService.validateLocationList(locationList);
        });

        invalidLocation.remove(Constant.LATITUDE);
        Assert.assertThrows(MyGateInvalidDataException.class, ()->{
            assignTruckService.validateLocationList(locationList);
        });
    }


}
