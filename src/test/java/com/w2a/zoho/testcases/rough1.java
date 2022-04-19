package com.w2a.zoho.testcases;

import com.w2a.zoho.PageObjects.*;
import com.w2a.zoho.utilities.*;
import org.testng.annotations.*;

public class rough1 extends BaseTest{
    @Test(dataProviderClass = DataProviders.class, dataProvider = "userDataFromExcel")
    public void test(String username, String password) {
        ExcelReader.excelIntoArray("src/test/resources/testdata/test.xlsx", "Sheet1");
        System.out.println(username+" "+password);

        openBrowser("chrome");

        DriverManager.getDriver().get("https://www.rahulshettyacademy.com/loginpagePractise/");

        rahulpage rahulpage=new rahulpage(DriverManager.getDriver());
        rahulpage.username.sendKeys(username);
        logInfo("Username is : "+username);
        rahulpage.password.sendKeys(password);
        logInfo("password is : "+password);
        rahulpage.signin.click();
        logInfo("Sign in to the page");




    }
    @AfterMethod
    public void tearDown() {

        logInfo("Test Completed");

        quit();

    }
}
