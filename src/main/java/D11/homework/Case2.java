package D11.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Set;

public class Case2 {
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

        WebElement submit = driver.findElement(By.className("mktoButton"));
        submit.click();

        WebElement reqMessage = driver.findElement(By.xpath("//div[@class='mktoError']"));
        reqMessage.isEnabled();
        reqMessage.getText();
        System.out.print("Message: " + reqMessage.getText());

//        element.clear();
//        driver.quit();
    }
}

