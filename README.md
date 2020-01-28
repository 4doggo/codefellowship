# CODEFELLOWSHIP
Contributer: Shingo Nakajima

## Steps to run app
1. Clone the app to your local machine
2. Edit the application.properties file 
    a. set db url to something you can access
        i. Create database in local machine 
               a. run psql in terminal
               b. CREATE DATABASE <database name>]
               c. run \c <database name> to connect to database
    b. set db username and password
3. run app with ./gradlew bootRun to run 
4. Open http://localhost:8080/
5. Once landing page opens then submit necessary information to create unique username and password
