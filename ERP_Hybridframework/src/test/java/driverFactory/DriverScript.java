package driverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import commonFunctions.FunctionaLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
	WebDriver driver;
	String inputpath ="./FileInput/DataEnginee.xlsx";
	String outputpath ="./FileOutput/HybridResults.xlsx";
	String TCSheet ="MasterTestCases";
	public void startTest()throws Throwable
	{
		String Module_Status="";
		String Module_New ="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc =xl.rowcount(TCSheet);
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			if(xl.getCellData(TCSheet, i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule =xl.getCellData(TCSheet, i, 1);
				for(int j=1;j<=xl.rowcount(TCModule);j++)
				{
					String Object_Type =xl.getCellData(TCModule, j, 1);
					String Locator_Type =xl.getCellData(TCModule, j, 2);
					String Locator_Value =xl.getCellData(TCModule, j, 3);
					String Test_Data =xl.getCellData(TCModule, j, 4);
                  try {
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver =FunctionaLibrary.startBrowser();
					}
					if(Object_Type.equalsIgnoreCase("openUrl"))
					{
						FunctionaLibrary.openUrl();
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						FunctionaLibrary.waitForElement(Locator_Type, Locator_Value, Test_Data);
						
					}
					if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						FunctionaLibrary.typeAction(Locator_Type, Locator_Value, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						FunctionaLibrary.clickAction(Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("validateTitle"))
					{
						FunctionaLibrary.validateTitle(Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						FunctionaLibrary.closeBrowser();
					}
					if(Object_Type.equalsIgnoreCase("dropDownAction"))
					{
						FunctionaLibrary.dropDownAction(Locator_Type, Locator_Value, Test_Data);
						
					}
					if(Object_Type.equalsIgnoreCase("capturestock"))
					{
						FunctionaLibrary.capturestock(Locator_Type, Locator_Value);;
						
					}
					if(Object_Type.equalsIgnoreCase("stockTable"))
					{
						FunctionaLibrary.stockTable();
						
					}
					if(Object_Type.equalsIgnoreCase("capturesupplier"))
					{
						FunctionaLibrary.capturesupplier(Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("suppliertable"))
					{
						FunctionaLibrary.suppliertable();
					}
					if(Object_Type.equalsIgnoreCase("capturecustomer"))
					{
						FunctionaLibrary.capturecustomer(Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("customertable"))
					{
						FunctionaLibrary.customertable();
					}
					
					
					
					xl.setCellData(TCModule, j, 5, "PASS", outputpath);
					Module_Status="True";
				} catch (Exception e) {
					System.out.println(e.getMessage());
					xl.setCellData(TCModule, j, 5, "FAIL", outputpath);
					Module_New="False";
				}
                	  
				}
				if(Module_Status.equalsIgnoreCase("True"))
				{
					xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
				}
				if(Module_New.equalsIgnoreCase("False"))
				{
					xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
				}
			}
			else
			{
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
	}

}
