"# mercury-tours-reservations" 

This **Reservations** project is small demo for Selenium-Java automation tests

The main test tool be used is Selenium using Java programming and running JUnit tests.
There are a lot of defects in the 'Mercury Tours' website. Intent of this project is to run one happy path test and a failing test.
Happy path test - BookRoundTripTest (successful creation of a round trip flight reservation)
Failing test - OneWayTripTest (failing test as one way radio button setting is not working. it continues as round trip.)

## Pre-requisites

* apache-maven-3.5.2
* jdk1.8.0_161
* git 
* Idea community edition (built using this IDE) or Eclipse
* Chrome or Firefox browser

## Installation

1. Install jdk (update path variable)
2. Install maven (update path variable)
3. Install IDE
4. Download project from Github
5. Open project in IDE as a maven project. All dependencies will be downloaded if there is connection to the internet.
6. Update setup.properties file if required
7. Reporting is set to INFO and to console. Update log4j2.xml is this needs to be changed.
8. Run tests from the IDE or from command promt using 'mvn test'. Running with maven will generate surefire report xmls.


### setup.properties ###

1. set url to be tested e.g. url=http://newtours.demoaut.com/mercurywelcome.php
2. set username e.g. username=mercury
3. set password e.g. password=mercury
4. set screenshots to true or false e.g. enable_screenshot=true
5. set browser to either chrome or firefox e.g. browser=chrome
6. set build name e.g.build=0.1


#### Logger ####
Currently the log level is set to *INFO* and to console. Update log4j2.xml to modify the log level and location
surefire-plugin report sample (for failing test)
-----------------------------------------------
check file in github 'Report TEST-flights.OneWayTripTest.xml'
Sample BookRoundTripTest log (since only info is logged, and there are no errors, the log is basic)
---------------------------------------------------------------------------------------------------
12:51:07.695 [main] INFO  flights.BookRoundTripTest - Starting test: BookRoundTrip_FirstClass_SinglePassenger_TicketlessTravel
Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 45199
Only local connections are allowed.
Feb 14, 2018 12:51:17 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
12:51:40.230 [main] INFO  flights.BookRoundTripTest - Completed successfully test: BookRoundTrip_FirstClass_SinglePassenger_TicketlessTravel

Sample OneWayTripTest log
-------------------------
12:51:41.085 [main] INFO  flights.BookRoundTripTest - Starting test: OneWayTrip_EconomyClass_SinglePassenger_TicketlessTravel
Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 46208
Only local connections are allowed.
Feb 14, 2018 12:51:44 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
12:51:52.901 [main] ERROR pageobjects.FlightFinderPage - Return date field selection is displayed. One way trip should not display Return date
12:51:52.903 [main] ERROR flights.BookRoundTripTest - Fail - Please check exception generated for test [OneWayTrip_EconomyClass_SinglePassenger_TicketlessTravel]
12:51:53.408 [main] INFO  common.BaseSetup - Screenshot [screenshot-OneWayTrip_EconomyClass_SinglePassenger_TicketlessTravel6195.png] captured

java.lang.Exception: return date fields are displayed for one way trip

	at pageobjects.FlightFinderPage.setOneWayTripFlightDetails(FlightFinderPage.java:203)
+	at flights.OneWayTripTest.OneWayTrip_EconomyClass_SinglePassenger_TicketlessTravel(OneWayTripTest.java:76)
	at org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
+    at org.junit.rules.RunRules.evaluate(RunRules.java:20)