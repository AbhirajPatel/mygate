package com.example.mygate.unit;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mygate.entity.MobileFood;
import com.example.mygate.repository.MobileFoodRepo;
import com.example.mygate.service.FoodService;
import com.example.mygate.util.DataLoader;
import com.example.mygate.exception.MyGateInvalidDataException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FoodServiceTest
{

    @InjectMocks private FoodService mobileFoodService;

    @Mock private MobileFoodRepo mobileFoodRepo;

    @BeforeClass void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Scenerio :
     * get expired certificate mobile food data;
     */
    @Test void getExpiredMobileFoodDataTest()
    {
        List<MobileFood> mobileFoods = new ArrayList<>();
        mobileFoods.add(DataLoader.getMobileFoodObject(DataLoader.getEntity1()));
        Mockito.when(mobileFoodRepo.findExpiredMobileDate(any())).thenReturn(mobileFoods);
        List<MobileFood> mobileFoodList = mobileFoodService.getExpiredMobileFoodData();
        Assert.assertEquals(mobileFoods.get(0), mobileFoodList.get(0));
    }

    /**
     * Scenarios:
     * When the list to save is proper;
     * When the list to save is Empty;
     */
    @Test void addMobileFoodTest()
            throws Exception
    {
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(DataLoader.getEntity3());
        mapList.add(DataLoader.getEntity2());

        List<MobileFood> mobileFoodList = new ArrayList<>();
        mobileFoodList.add(DataLoader.getMobileFoodObject(DataLoader.getEntity3()));
        mobileFoodList.add(DataLoader.getMobileFoodObject(DataLoader.getEntity2()));

        Mockito.when(mobileFoodRepo.saveAll(any())).thenReturn(mobileFoodList);
        List<MobileFood> mobileFoodsAfterSave = mobileFoodService.addMobileFood(mapList);

        Assert.assertFalse(mobileFoodsAfterSave.isEmpty());
        Assert.assertEquals(mobileFoodsAfterSave.size(), 2);

        Mockito.when(mobileFoodRepo.saveAll(any())).thenReturn(new ArrayList<>());
        mobileFoodsAfterSave = mobileFoodService.addMobileFood(new ArrayList<>());
        Assert.assertTrue(mobileFoodsAfterSave.isEmpty());
    }

    /**
     * Test for throwing the exception when saving payload is not proper;
     *
     * @throws Exception
     */
    @Test void addMobileFoodTestThrowingException() throws Exception
    {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> invalidMap = new HashMap<>();
        invalidMap.put("string", 123);
        invalidMap.put("latitude", "wrong");
        invalidMap.put("expirationDate", new MobileFood());
        mapList.add(invalidMap);
        Assert.assertThrows(MyGateInvalidDataException.class, () -> {
            mobileFoodService.addMobileFood(mapList);
        });
    }

}

