package D11;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;
import static java.awt.SystemColor.window;

public class SwitchTabs {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //open first web
        driver.get("https://saucelabs.com/request-demo");
        System.out.println("Tab 1: " + driver.getTitle());

        //save the origin ID
        String originalTab = driver.getWindowHandle();

        //open another tab to access google
        ((JavascriptExecutor) driver).executeScript("window.open('https://google.com', '_blank')");

        //get all ID in the tab
        Set<String> allTabs = driver.getWindowHandles();

        //switch to the new tab
        for (String tab : allTabs){
            if (!tab.equals(originalTab)){
                driver.switchTo().window(tab);
                break;
            }
        }


        WebElement element1 = driver.findElement(By.id("APjFqb"));
        element1.sendKeys("Find the best image");


//        element.clear();
//        driver.quit();
    }
}
