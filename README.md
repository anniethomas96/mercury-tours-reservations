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
8. Run tests from the IDE itself. (mvn test should also work)


### setup.properties ###

1. set url to be tested e.g. url=http://newtours.demoaut.com/mercurywelcome.php
2. set username e.g. username=mercury
3. set password e.g. password=mercury
4. set screenshots to true or false e.g. enable_screenshot=true
5. set browser to either chrome or firefox e.g. browser=chrome
6. set build name e.g.build=0.1


#### Logger ####
Currently the log level is set to *INFO* and to console. Update log4j2.xml to modify the log level and location
