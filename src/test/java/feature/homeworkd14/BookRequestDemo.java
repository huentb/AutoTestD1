package feature.homeworkd14;

import D13.homework.exercise1.RequestDemoPage;
import common.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.*;

public class BookRequestDemo {
    WebDriver driver;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("===== Test Suite Started =====");
    }

    @BeforeClass // chỉ mở trang web duy nhất 1 lần
    public void setUpClass() {
        System.out.println("===== Test Class Setup =====");
    }

    @BeforeMethod // mỗi test mở lại trang mới
    public void setUp() {
        System.out.println("===== Before Each Test Method =====");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void inputValidData() {
        driver.get("https://saucelabs.com/request-demo");

        //nhap data vao page oject trong main
        String excelFilePath = "D:\\Automation Testing\\AutoTest\\AutoTestD1/dataInfo.xlsx";
        String sheetName = "Sheet1";

        //doc du lieu tu file excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        RequestDemoPage demoPage = new RequestDemoPage(driver);


        Map<String, String> data = excelData.get(0);

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
        //xac nhan nhap dung data, khong có alert
        List<WebElement> alerts = driver.findElements(By.xpath("//div[@role='alert']"));
        Assert.assertTrue(alerts.isEmpty(), "No alert on the screen!");
    }

    @Test
    public void interestDataIsMissing() throws InterruptedException {
        driver.get("https://saucelabs.com/request-demo");

        //nhap data vao page oject trong main
        String excelFilePath = "D:\\Automation Testing\\AutoTest\\AutoTestD1/dataInfo.xlsx";
        String sheetName = "Sheet1";

        //doc du lieu tu file excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        RequestDemoPage demoPage = new RequestDemoPage(driver);

        //nhap row 1 khong có data Interest
        Map<String, String> data = excelData.get(1);

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

        WebElement submitButon = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButon.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement interestReqMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ValidMsgSolution_Interest__c']"))
        );

        //xac nhan hien thi require message
        String actualMsg = interestReqMsg.getText();
        String expetedMsg = "This field is required.";
        Assert.assertEquals(actualMsg, expetedMsg);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("===== After Each Test Method =====");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("===== Test Class Tear Down =====");
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("===== Test Suite Complete =====");
    }
}
