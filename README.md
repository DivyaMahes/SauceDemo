# SauceDemo
This project deals with automating the below two features of https://www.saucedemo.com/
- Login Form 
- Product page

### Requirements
Automation Tool : Selenium Webdriver<br/>
Build Tool : Maven<br/>
Version control : Git<br/>
Framework : TestNG and POM<br/>
Programming language : Java<br/>
Browser : Chrome<br/>
Report : ExtentSparkReport

### Framework
src/main/java has 3 packages - Report, Utility, PageClass <br/>
#### Report Package :
- ExtentReportManager.java - Responsible for generating test execution report <br/>
#### Utility Package : 
- BrowserManager.java - Responsible for initiating webdriver and Browser launch. It extends 'ExtentReportManager.java'
- TestData.java - Maintains all the test data <br/>
#### PageClass Package: It hold the classes created for each page of the application
- PageClassManager -- Maintain the object of all page classes (as descriped below) and reference is passed to all child page classes. It extends 'BrowserManager.java'
- LoginPage.java -- Handles login page validation
- StandardUserProduct -- Handles standard User login page validation - ProductDetails, ProductDescription, Hamburger Menu and Sorting
- ProblemUserProduct -- Handles Product detail validation for a Problem User
- Cart.java -- Handles Cart realted validation <br/>

src/test/java - Holds the package 'saucedemo.TestCases' to maintain test cases/ test scripts
- LoginTest.java - Test script to validate the
    - Login form Title
    - Error Message shown for invalid user login
    - Standard User 
    - Problem User
