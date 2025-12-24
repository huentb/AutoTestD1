package D13;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class ExcelFileReader {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //open web
        driver.get("https://www.saucedemo.com/");

        //Login
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement login = driver.findElement(By.id("login-button"));
        login.click();
    }
}
