package D11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElement {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();;
        driver.get("https://saucelabs.com/request-demo");

        WebElement element1 = driver.findElement(By.id("Email"));
        element1.sendKeys("huentb@gmail.com");
        WebElement element2 = driver.findElement(By.className("mktoButton"));
        element2.click();
        WebElement element3 = driver.findElement(By.xpath("//div[@class='mktoError']"));
        element3.isEnabled();
        element3.getText();
        System.out.print("Message: " + element3.getText());

//        element.clear();
//        driver.quit();
    }
}
