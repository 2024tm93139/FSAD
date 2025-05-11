# FSAD Project setup guide.
This repository contain code for both frontend and backend part of assignment. Both codebase are independent and can be deployed separately. 
Frontend is created using React. Consuming exposed backend API using fetch and showing data on frontend.
Backend is built on Java using Spring boot. Database is MySql and CRUD operations are handled by Hibernate (an ORM framework).

Code Setup guideline -
Prerequisite - npm and jdk 17 need to be installed in you system in order to run the project.
  1. **For backend** - Move inside **FSD-Assignment** folder of your checkedout code. Open CMD at this location and run **'mvnw clean install'**. Once command is successfully executed, check where is your jar file created. It should be inside **target** folder. Once you have verified your jar file location, execute this command - java -jar {jar_location}/{jar_name} com.bits.fsd.FsdAssignmentApplication and wait for server to be started. Once it is started, move to setting up next part.
  2. **For frontend** - Move inside **FSD-Assignment-frontend** folder of your checked out code. Open CMD here and just run '**npm run dev**'. This step may take some time if you are running npm for first time as it needs to download dependency from web. Application is started, copy the url shown on screen and open it in a browser.

You should be able to see the login page. Enter any credential and you will land on welcome page. From there you can perform action to create and manage student/drive and all will be done without refreshing page.
