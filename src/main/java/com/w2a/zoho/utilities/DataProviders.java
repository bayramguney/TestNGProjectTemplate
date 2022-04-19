package com.w2a.zoho.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "userDataFromExcel",parallel = true)
    public Object[][] getData2() {
        return ExcelReader.excelIntoArray("src/test/resources/testdata/test.xlsx", "Sheet1" );
    }
	

	


}
