package feature;

import com.google.common.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.annotations.*;

public class LoginMultiCaseTest {
    WebDriver driver;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("===== Test Suite Started =====");
    }

    @BeforeMethod // mỗi test mở lại trang mới
    public void setUp() {
        System.out.println("===== Before Each Test Method =====");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeClass // chỉ mở trang web duy nhất 1 lần
    public void setUpClass() {
        System.out.println("===== Test Class Setup =====");
    }

    @Test
    public void testLoginWithValidCredentials() {
        driver.get("https://www.saucedemo.com/");

        //kiem tra tieu de va url
        Assert.assertEquals(driver.getTitle(), "Swag Labs",
                "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/",
                "URL mismatch!");

        //dien thong tin dung
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //xac nhan login thanh cong, vao trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "Home URL mismatch!"); //co the ktra them cac element khac
    }

    @Test
    public void testLoginWithUsernameNonExistentUser() {
        driver.get("https://www.saucedemo.com/");

        //dien thong tin dung
        driver.findElement(By.id("user-name")).sendKeys("standard_user1");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //bao loi
        WebElement errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(),"Error message is display!");
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match"),"Failed to log in with a non-existent user!");
    }

    @Test
    public void testLoginWithLockedOutUser() {
        driver.get("https://www.saucedemo.com/");

        //dien thong tin dung
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //bao loi
        WebElement errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(),"Error message is display!");
        Assert.assertTrue(errorMsg.getText().contains("Sorry, this user has been locked out"),"Fail to login with locked out user!");
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("===== Test Class Tear Down =====");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("===== After Each Test Method =====");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("===== Test Suite Complete =====");
    }

}
