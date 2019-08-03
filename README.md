
# mygate
There is a setup file to run this app.
just run the set up file.


# Dependency :
   1. java 8
   2. mvn should be install
   3. docker should be install
   4. spring boot version used is 2.1.6;
  
I am using postgres data base for this, and a custome postgres image is pushed to docker hub.
setup file will pul the image and start the container with proper configurations. 

This is an spring boot app with loaded data in postgres data base running as docker container;
There are some unit test and integration test are already written. 

In order to run the Integration test make sure that app is running locally with port 8080, this will hit to localhost:8080. 
host value and post url can be changed. 

# Given one or multiple locations, predict which truck will be the best one to assign the job.
   Assumptions : 
        I will look for the latitude and longitude within range of +100 and -100. 
        For ex: for the single location to find the best truck -- 
        load all the trcuk as -- FROM MobileFood WHERE longitude > lat-100 and longitude < lat+100 and latitude > lon-100 and latitude < lon+100
  Then will find the set (union) of all the truck and then find out the truck that will take the least time to cover all the location. 
  I have avoided checking expirationdate, already booked, etc condition for find the best truck;
  Best truck is founded based on distance criteria for now. 
  
#Code
 Code is written with proper documentation. 
 Modularity is followed. 
 SOLID principle is followed as well. 
 Highly maintainable code, doing changes are less expensive. 
 Scalibilty is also taken care. 
 Unit tests as well as integration cases are also written. (but not with full coverage). 
 The bed for writting the test is already set, new test cases can easily added. 
 
 
 #Go with code to see the quality. 
