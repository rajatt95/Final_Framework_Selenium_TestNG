# Framework_Selenium_TestNG_Final
This framework is designed and developed for Web Automation Testing (Framework_Selenium_TestNG_Final); Supports Parallel testing as well.

--------------------------------------------------------------------------------------------------------------------

1.  Automation testing tool - Selenium WebDriver API
2.  Programming language - Java
3.  IDE - Eclipse
4.  Logging - Log4J
5.  Design Pattern - Page Object Model (With Page Factories)
6.  Testing framework - TestNG
7.  Build and Dependency Management Tool - Maven
8.  Reports - ExtentReports V5, TestNG reports (Emailable-reports.html)
9.  Approach - Data-Driven (.xlsx)
-----------------
Other Utilities implemented - 
1. **Java Mail API** - You'll be able to receive an Email notification (with Test cases count in message body and reports - ExtentReport, Emailable-Reports) after the Test suite Execution. 
(You can send Email to more than single User as well, can attach more files with the Email)
![image](https://user-images.githubusercontent.com/26399692/132939837-da66c77d-4c65-4b5f-b721-c1a64b51ba85.png)

2. **Zip file creation** - a zip will created of all the ExtentReports after Test suite Execution.
3. **Multiple Extent Reports (All and Only PASS/SKIP/FAIL)**
-----------------
Files Location - 
1. Browser selection : Project Directory/src/test/resources/properties/environment.properties
2. Test Data Excel File : Project Directory/src/test/resources/excel/testdata.xlsx
3. **TestNG.XML Runner files : Project Directory/src/test/resources/runner/**
4. Logs : Project Directory/logs
5. **ExtentReports : Project Directory/extent_reports/**
6. Zip file of Extent Reports : Project Directory/Extent_Reports.zip
7. EMail credentials : Project Directory/src/main/java/com/learning/email/java_Mail_API/TestConfig.java
8. **Comparison (Sequential/Parallel run) : Project Directory/Test_Execution_Reports_Analysis/**
9. Test scripts : Project Directory/src/test/java/com/learning/actual/testscripts/

