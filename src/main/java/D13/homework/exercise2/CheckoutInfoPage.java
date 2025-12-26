package D13.homework.exercise2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class CheckoutInfoPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    public void fillForm(String firstName,
                         String lastName,
                         String postalCode) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }
}
