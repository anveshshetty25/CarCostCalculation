# Car Cost Calculation
This application helps user to give the cost of a car for type Coupe,SUV,Truck and Luxary_sedan. User can add options like V8,Automatic,Premium Audio,Sunroof,Navigation and TowPackage.
# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
## Preqrequisites ##
Install Java JDK7 on your local machine and an IDE of your choice (e.g., Eclipse, IntelliJ, JbossDeveloperStudio). To run the application, download and install tomcat v7 server on your machine.
## Setup ###
Copy the URL from github,Open Eclipse follow the steps, File->import->Git->project from git->URI and paste the copied URL, give your credentials then you can see the project in your eclipse.Now convert it into Maven project.Create a log file in your machine, copy the path and modify the log4j.properties file property(log4j.appender.file.File) with the path of your log file.  
## Build ##
Open eclipse and right click on your project choose run as Maven clean and Maven Install, Artifacts are generated.Now you can deploy the App on your Tomcat Server.
## Running the tests ##
you can traverse src/test/java folder and open CarCostCalculationTest.java class and right click on it to run Junit tests.This process is part of Maven install task.These Test cases tests the endpoint with all possible scenarios.
## Deployment ##
I have used Heroku archetype of maven to create the project and we can deploy the Application on Heroku with GitHub Continous Integration with Heroku.
## Built with ##
Maven- Dependency Management
## Authors ##
* **Anwesh Valleshetti**
## Acknowledgements ##
* https://getbootstrap.com/docs/4.1/
* https://colorlib.com/wp/free-404-error-page-templates/
