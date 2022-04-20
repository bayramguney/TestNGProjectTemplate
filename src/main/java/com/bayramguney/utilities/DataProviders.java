package com.bayramguney.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "userDataFromExcel")
    public Object[][] getData2() {
        return ExcelReader.excelIntoArray("src/test/resources/testdata/test.xlsx", "Sheet1" );
    }
	

	


}
