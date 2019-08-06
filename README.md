
# mygate
This app is hosted ver ec2. 

git clone https://github.com/AbhirajPatel/mygate.git
Go to this local repository;

# Dependency :
   1. java 8
   2. mvn should be install
   3. docker should be install
   4. spring boot version used is 2.1.6;
  
I am using postgres data base for this as docker container running over ec2 instance; 
All the data are loaded in the pstgres container. 

There are some unit test and integration test are already written. 
Integration test will hit thr api f app runiing in aws, this can be used to test the hsted app. 

Host value and post url can be changed. 

# Apis 
   considering that it is running in aws in port 8080
   
   1. http://ec2-3-17-67-157.us-east-2.compute.amazonaws.com:8080/mygate/applicant/{name} -- GET -- get the MobileFood data               with applicant name.
   
   
   2. http://ec2-3-17-67-157.us-east-2.compute.amazonaws.com:8080/mygate/street/{name} -- GET -- get the MobileFood data with             street name. 
   
   3. http://ec2-3-17-67-157.us-east-2.compute.amazonaws.com:8080/mygate/expired    -- GET -- get all Mobillefood data whose             certificate is expired. 
   
   4. http://ec2-3-17-67-157.us-east-2.compute.amazonaws.com:8080/mygate/delete/{id} -- DELETE -- delete the specific row with           given id if present; 
   
   
   5. http://ec2-3-17-67-157.us-east-2.compute.amazonaws.com:8080/mygate/addDate -- POST -- add the list of MobileFood data (value      can be provided as body);
     
     
     exapmple paylad = 
      
      [
        {
         "priorPermit"=priorPermit2,
         "cnn"=1232,
         "address"=address2,
         "facilityType"=facilityType2, 
         "locationDescription"=locationDescription2, 
         "blocklot"=blocklot2, 
         "latitude"=1232.0, 
         "nOISent"=nOISent2, 
         "received"=received2, 
         "expirationdate"=2019-08-04, 
         "applicant"=applicant2, 
         "lot"=lot2, 
         "schedule"=schedule2, 
         "approved"=approved2, 
         "locationid"=locationid2, 
         "permit"=permit2, 
         "x"=1232.0, 
         "y"=1232.0, 
         "block"=block2, 
         "location"=location2, 
         "foodItems"=foodItems2, 
         "status"=status2, 
         "longitude"=1232.0
        }
     ]

     Return will be the savedMobile fod enitiy with id. 
     
   6. http://ec2-3-17-67-157.us-east-2.compute.amazonaws.com:8080/mygate/truck -- GET -- get the truck for given list of location in body. 
      We have to provid the location list as payload in bosy; 
      
     Example payload =
     {
     "location"=
       [
          {
           "latitude"=1242.23, 
           "longitude"=1235.12
           },
           {
           "latitude"=1242.23, 
           "longitude"=1125.0
           }
        ]
      }
    
    
   Return will be the best MobileFood Enity that will suit the best trcuk for delivery. 
     
     
  
 Details of using this api can be referenced from the code. 
 
 Note:  Here the assumption is that, we are not validating over any fields to be mandatory to be part of the mobile food data.
 
 # There are some integration test that are testing this apis,  as well in codebase. 

# Given one or multiple locations, predict which truck will be the best one to assign the job.
   Assumptions : 
        I will look for the latitude and longitude within range of +100 and -100. 
        For ex: for the single location to find the best truck -- 
        load all the trcuk as -- FROM MobileFood WHERE longitude > lat-100 and longitude < lat+100 and latitude > lon-100 and latitude < lon+100
  Then will find the set (union) of all the truck and then find out the truck that will take the least time to cover all the location. 
  I have avoided checking expirationdate, already booked, etc condition for find the best truck;
  Best truck is founded based on distance criteria for now. 
  
# Code
 Code is written with proper documentation. 
 Modularity is followed. 
 SOLID principle is followed as well. 
 Highly maintainable code, doing changes are less expensive. 
 Scalibilty is also taken care. 
 Unit tests as well as integration cases are also written. (but not with full coverage). 
 The bed for writting the test is already set, new test cases can easily added. 
 
 
 # Go with code to see the quality. 
 # All the logics and use cases are properly documented over code. 
