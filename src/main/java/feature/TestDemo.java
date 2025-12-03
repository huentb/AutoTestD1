package feature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDemo {
    static WebDriver driver = null;

    public static void main(String[] agrs) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();;
        driver.get("https://serenity-bdd.github.io/");
//        driver.quit();
    }
}
