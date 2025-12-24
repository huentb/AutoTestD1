package D13.homework.exercise1;

import common.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

public class RequestDemoTest {
    public static void main(String[] args) {
        String excelFilePath = "D:\\Automation Testing\\AutoTest\\AutoTestD1/dataInfo.xlsx";
        String sheetName = "Sheet1";

        //doc du lieu tu file excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);
        //thiet lap webdriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        RequestDemoPage demoPage = new RequestDemoPage(driver);


        for (Map<String, String> data : excelData) {

            demoPage.open();

            demoPage.fillForm(
                    data.get("Business Email"),
                    data.get("First Name"),
                    data.get("Last Name"),
                    data.get("Company"),
                    data.get("Phone Number"),
                    data.get("Country"),
                    data.get("Interest"),
                    data.get("Comments")
            );
        }
//        driver.quit();
    }
}