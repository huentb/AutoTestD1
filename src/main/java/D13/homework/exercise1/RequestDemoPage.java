package D13.homework.exercise1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class RequestDemoPage {

    WebDriver driver;
    WebDriverWait wait;

    public RequestDemoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://saucelabs.com/request-demo");
    }

    public void fillForm(String businessMail,
                         String firstName,
                         String lastName,
                         String company,
                         String phoneNumber,
                         String country,
                         String interest,
                         String comment) {

        driver.findElement(By.id("Email")).sendKeys(businessMail);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Company")).sendKeys(company);
        driver.findElement(By.id("Phone")).sendKeys(phoneNumber);
        Select countryDropdown =
                new Select(driver.findElement(By.name("Country")));
        countryDropdown.selectByVisibleText(country);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.name("Solution_Interest__c")));
        Select interestDropdown =
                new Select(driver.findElement(By.name("Solution_Interest__c")));
        interestDropdown.selectByVisibleText(interest);
        driver.findElement(By.id("Sales_Contact_Comments__c")).sendKeys(comment);

    }
}
