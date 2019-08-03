package com.example.mygate.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.mygate.util.Constant;
import org.apache.commons.beanutils.BeanUtils;

@Entity()
@Table(name="mobile_food")
public class MobileFood
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "locationid")
    private Integer     locationid;

    @Column(name = "applicant")
    private String  applicant;

    @Column(name = "facilityType")
    private String  facilityType;

    @Column(name = "cnn")
    private Integer     cnn;

    @Column(name = "locationdescription")
    private String  locationDescription;

    @Column(name = "address")
    private String  address;

    @Column(name = "blocklot")
    private String  blocklot	;

    @Column(name = "block")
    private String  block;

    @Column(name = "lot")
    private String  lot;

    @Column(name = "permit")
    private String  permit;

    @Column(name = "status")
    private String  status;

    @Column(name = "fooditems")
    private String  foodItems;

    @Column(name = "x")
    private Float   x;

    @Column(name = "y")
    private Float   y;

    @Column(name = "latitude")
    private Float   latitude;

    @Column(name = "longitude")
    private Float   longitude;

    @Column(name = "schedule")
    private String  schedule;

    @Column(name = "noisent")
    private String  nOISent;

    @Column(name = "approved")
    private String  approved	;

    @Column(name = "received")
    private String  received;

    @Column(name = "priorpermit")
    private Boolean  priorPermit;

    @Column(name = "expirationdate")
    private Date expirationDate;

    @Column(name = "location")
    private String location;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

            for (String key : Constant.getAllConstants())
            {
                try
                {
                    stringBuilder.append(key + " : " + BeanUtils.getProperty(this, key));
                    stringBuilder.append("\n");
                }catch (Exception e){
                    // Do nothing;
                }
            }
        return stringBuilder.toString();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getLocationid()
    {
        return locationid;
    }

    public void setLocationid(Integer locationid)
    {
        this.locationid = locationid;
    }

    public String getApplicant()
    {
        return applicant;
    }

    public void setApplicant(String applicant)
    {
        this.applicant = applicant;
    }

    public String getFacilityType()
    {
        return facilityType;
    }

    public void setFacilityType(String facilityType)
    {
        this.facilityType = facilityType;
    }

    public Integer getCnn()
    {
        return cnn;
    }

    public void setCnn(Integer cnn)
    {
        this.cnn = cnn;
    }

    public String getLocationDescription()
    {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription)
    {
        this.locationDescription = locationDescription;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getBlocklot()
    {
        return blocklot;
    }

    public void setBlocklot(String blocklot)
    {
        this.blocklot = blocklot;
    }

    public String getBlock()
    {
        return block;
    }

    public void setBlock(String block)
    {
        this.block = block;
    }

    public String getLot()
    {
        return lot;
    }

    public void setLot(String lot)
    {
        this.lot = lot;
    }

    public String getPermit()
    {
        return permit;
    }

    public void setPermit(String permit)
    {
        this.permit = permit;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getFoodItems()
    {
        return foodItems;
    }

    public void setFoodItems(String foodItems)
    {
        this.foodItems = foodItems;
    }

    public Float getX()
    {
        return x;
    }

    public void setX(Float x)
    {
        this.x = x;
    }

    public Float getY()
    {
        return y;
    }

    public void setY(Float y)
    {
        this.y = y;
    }

    public Float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Float latitude)
    {
        this.latitude = latitude;
    }

    public Float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Float longitude)
    {
        this.longitude = longitude;
    }

    public String getSchedule()
    {
        return schedule;
    }

    public void setSchedule(String schedule)
    {
        this.schedule = schedule;
    }

    public String getnOISent()
    {
        return nOISent;
    }

    public void setnOISent(String nOISent)
    {
        this.nOISent = nOISent;
    }

    public String getApproved()
    {
        return approved;
    }

    public void setApproved(String approved)
    {
        this.approved = approved;
    }

    public String getReceived()
    {
        return received;
    }

    public void setReceived(String received)
    {
        this.received = received;
    }

    public Boolean getPriorPermit()
    {
        return priorPermit;
    }

    public void setPriorPermit(Boolean priorPermit)
    {
        this.priorPermit = priorPermit;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
