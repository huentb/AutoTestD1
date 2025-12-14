package D11.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Set;

public class Case1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //open first web
        driver.get("https://saucelabs.com/request-demo");

        //input
        WebElement element1 = driver.findElement(By.id("Email"));
        element1.sendKeys("huentb98@gmail.com");

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element2 = wait1.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))
        );
        element2.sendKeys("Hue");

        WebElement element3 = driver.findElement(By.id("LastName"));
        element3.sendKeys("Nguyen");

        WebElement element4 = driver.findElement(By.id("Company"));
        element4.sendKeys("VNEXT JSC.");

        WebElement element5 = driver.findElement(By.id("Phone"));
        element5.sendKeys("0364652125");

        WebElement element6 = driver.findElement(By.xpath("//option[@value='Vietnam']"));
        element6.click();

        WebElement element7 = driver.findElement(By.xpath("//option[@value='Scalable Test Automation']"));
        element7.click();

        WebElement element8 = driver.findElement(By.id("Comment"));
        element8.sendKeys("No comment!");

//        element.clear();
//        driver.quit();
    }
}

