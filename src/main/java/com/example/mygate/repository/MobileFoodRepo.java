package com.example.mygate.repository;

import java.sql.Date;
import java.util.List;

import com.example.mygate.entity.MobileFood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileFoodRepo extends CrudRepository<MobileFood, Long>
{
    /**
     * Find the Mobile food item by id;
     * @param id : id
     * @return : MobileFood Enitiy
     */
    public MobileFood findOneById(Long id);

    /**
     * Find all Mobile Food Item by applicant name;
     * @param applicant : applicants name
     * @return List of MibileFoodEntity
     */
    public List<MobileFood> findByApplicant(String applicant);

    /**
     * Find all mobile_Food item by Street name;
     * @param address : name of street;
     * @return List of MobileFood enity with given stree name;
     */
    public List<MobileFood> findAllByAddress(String address);

    /**
     * Find all the expired data;
     * @param date : current date;
     * @return : MobileFood entity whose permits are expired.
     */
    @Query("FROM MobileFood WHERE expirationdate < ?1")
    public List<MobileFood> findExpiredMobileDate(Date date);

    /**
     * Find all the un-expired data;
     * @param date : current date;
     * @return : MobileFood entity whose permits are not yet expired..
     */
    @Query("FROM MobileFood WHERE expirationdate > ?1")
    public List<MobileFood> findUnExpiredMobileDate(Date date);

    /**
     * Find the Mobile truck in given location range;
     * @param longitude1 :
     * @param longitude2 :
     * @param latitude1  :
     * @param latitude2  :
     * @return :
     */
    @Query("FROM MobileFood WHERE longitude > ?1 and longitude < ?2 and latitude > ?3 and latitude < ?4")
    public List<MobileFood> findMobileFoodLocationRange(Float longitude1, Float longitude2, Float latitude1, Float latitude2);

}
