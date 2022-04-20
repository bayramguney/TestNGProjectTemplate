package com.bayramguney.testcases;

import com.bayramguney.PageObjects.*;
import com.bayramguney.utilities.*;
import org.testng.*;
import org.testng.annotations.*;

public class rough extends BaseTest{
    @Test(dataProviderClass = DataProviders.class, dataProvider = "userDataFromExcel")
    public void test(String username, String password) {
        ExcelReader.excelIntoArray("src/test/resources/testdata/test.xlsx", "Sheet1");
        System.out.println(username+" "+password);

        openBrowser("chrome");

        DriverManager.getDriver().get("https://www.rahulshettyacademy.com/loginpagePractise/");


       // waitVisibility(rahulpage.username);
        sleep(3);
        rahulpage.username.sendKeys(username);
        logInfo("Username is : "+username);
       // waitVisibility(rahulpage.password);
        sleep(3);
        rahulpage.password.sendKeys(password);
        logInfo("password is : "+password);
        waitClickability(rahulpage.signin);
        rahulpage.signin.click();
        waitVisibility(rahulpage.errormessage);
        Assert.assertTrue(rahulpage.errormessage.getText().contains("Incorrect"));
        logInfo("Sign in to the page");




    }
    @AfterMethod
    public void tearDown() {

        logInfo("Test Completed");

        quit();

    }
}
