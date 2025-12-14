package D11;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetUrl {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();;
        driver.get("https://saucelabs.com");
        driver.navigate().to("https://saucelabs.com/request-demo");
//        Thread.sleep(500);
        driver.navigate().back(); // ve link 1
//        driver.navigate().forward(); // den link 2
//        driver.navigate().refresh();
        String url = driver.getCurrentUrl();
        System.out.print("String url la: " + url);
        driver.quit();
    }

}
