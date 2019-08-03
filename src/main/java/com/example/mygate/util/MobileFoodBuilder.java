package com.example.mygate.util;

import java.util.Map;

import com.example.mygate.entity.MobileFood;
import com.example.mygate.exception.MyGateInvalidDataException;
import org.apache.commons.beanutils.BeanUtils;

public class MobileFoodBuilder
{

    /**
     * Get the mobileFood entity object from the given Data;
     * @param map :
     * @return :
     * @throws MyGateInvalidDataException :
     */
    public static MobileFood getObjectFromMap(Map<String, Object> map) throws MyGateInvalidDataException
    {
        MobileFood mobileFood = new MobileFood();
        try
        {
            for (String key : Constant.getAllConstants())
            {
                if ( map.containsKey(key) )
                {
                    BeanUtils.setProperty(mobileFood, key, map.get(key).toString());
                }
            }
        }catch (Exception e){
            throw new MyGateInvalidDataException(e.getMessage());
        }
        if(!map.containsKey(Constant.APPLICANT)){
            throw new MyGateInvalidDataException();
        }
        return mobileFood;
    }
}
