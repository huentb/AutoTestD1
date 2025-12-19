package D12.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class UserCheckoutFlowTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("user-data-dir=C:\\selenium\\chrome-profile");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //open the web
        driver.get("https://www.saucedemo.com/");

        //Step1 : Login
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement login = driver.findElement(By.id("login-button"));
        login.click();

        //Step 2: Chọn tìm kiếm droplist Price (low to high)
        WebElement filter = driver.findElement(By.className("product_sort_container"));
        filter.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement filterList = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='lohi']"))
        );

        Select selectFilter = new Select(filter);
        selectFilter.selectByValue("lohi");
        Thread.sleep(30);
        //Step 3: Add to cart 2 sản phẩm bất kì
        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        //add 2 sản phẩm đầu tiên trong list
        for (int i = 0; i < 2; i++) {
            WebElement product = products.get(i);
            // click Add to cart
            product.findElement(By.tagName("button")).click();
        }

        //check cart
        WebElement cartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        int count = Integer.parseInt(cartBadge.getText());
        if (count == 2) {
            System.out.println("Số lượng sản phẩm trong giỏ hàng là " + cartBadge.getText());
        } else {
            System.out.println("Số lượng sản phẩm không đúng!");
        }

        //Step 4: Click vào giỏ hàng và check data đã add
        cartBadge.click();

        List<WebElement> productListInCart = driver.findElements(By.className("cart_item"));

        //check data
        for (int i = 0; i < 2; i++) {
            WebElement product = productListInCart.get(i);
            // get tên sản phẩm
            String productName = product
                    .findElement(By.className("inventory_item_name"))
                    .getText();
            // get price
            String price = product
                    .findElement(By.className("inventory_item_price"))
                    .getText();

            if (productName.equals("Sauce Labs Onesie") && price.equals("$7.99")) {
                System.out.println("Thông tin sản phẩm 1 chính xác!");
                System.out.println(productName + " - " + price);
            } else if (productName.equals("Sauce Labs Bike Light") && price.equals("$9.99")) {
                System.out.println("Thông tin sản phẩm 2 chính xác!");
                System.out.println(productName + " - " + price);
            } else {
                System.out.println("Thông tin sản phẩm không chính xác: " + productName + " - " + price);
            }
        }

        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));
        int countRemoveButton = removeButtons.size();
        System.out.println("Màn hình hiển thị " + countRemoveButton + " button Remove");

        // Step 5: Click Checkout và nhập các thông tin First name, Last name, Zip code
        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();

        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Hue");

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Nguyen");

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("850-0001");

        //Step 6: Click continute
        WebElement submit = driver.findElement(By.id("continue"));
        submit.click();

        List<WebElement> overview = driver.findElements(By.className("cart_item"));

        //check data
        for (int i = 0; i < 2; i++) {
            WebElement product = overview.get(i);
            // get productName
            String productName = product
                    .findElement(By.className("inventory_item_name"))
                    .getText();
            // get description
            String description = product
                    .findElement(By.className("inventory_item_desc"))
                    .getText();

            if (description.equals("Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.")) {
                System.out.println("Thông tin mô tả sản phẩm " + productName + " chính xác!");
                System.out.println("Description: " + description);
            } else if (description.equals("A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.")) {
                System.out.println("Thông tin mô tả sản phẩm " + productName + " chính xác!");
                System.out.println("Description: " + description);
            } else {
                System.out.println("Thông tin mô tả sản phẩm " + productName + " không chính xác!");
            }
        }

        // check don vi van chuyen
        WebElement shippingInfo = driver.findElement(By.xpath("//div[@data-test='shipping-info-value']"));
        shippingInfo.getText();
        if (shippingInfo.getText().equals("Free Pony Express Delivery!")) {
            System.out.println("Shipping Information là: " + shippingInfo.getText());
        } else {
            System.out.println("Shipping Information không phải là Free Pony Express Delivery!");
        }
        // check tong tien
        double unitPrice1 = 7.99;
        int quantity1 = 1;
        double unitPrice2 = 9.99;
        int quantity2 = 1;
        double taxable = 0.08;

        double expectedSubTotal = unitPrice1 * quantity1 + unitPrice2 * quantity2;
        double expectedTotal = expectedSubTotal * (1 + taxable);
        //ep kieu expected sub total
        String expectedFormatSubTotal = String.format("%.2f", expectedSubTotal);
        //ep kieu expected total
        String expectedFormatTotal = String.format("%.2f", expectedTotal);

        // check subTotal tren man hinh
        WebElement subTotalLable = driver.findElement(By.className("summary_subtotal_label"));
        subTotalLable.getText();
        String actualSubTotal =
                subTotalLable.getText().replace("Item total: $", "").trim(); //cat chuoi de lay so
        if (actualSubTotal.equals(expectedFormatSubTotal)) {
            System.out.println("Tổng tiền trước thuế hiển thị chính xác = " + actualSubTotal);
        } else {
            System.out.println("Tổng tiền trước thuế hiển thị không chính xác! Vui lòng kiểm tra!");
        }
        // check total tren man hinh
        WebElement totalLable = driver.findElement(By.className("summary_total_label"));
        totalLable.getText();
        String actualTotal =
                totalLable.getText().replace("Total: $", "").trim(); //cat chuoi de lay so
        if (actualTotal.equals(expectedFormatTotal)) {
            System.out.println("Tổng tiền sau thuế hiển thị chính xác = " + actualTotal);
        } else {
            System.out.println("Tổng tiền trước thuế hiển thị không chính xác! Vui lòng kiểm tra!");
        }

        //check button Finish
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.isEnabled();
        if (finishButton.isEnabled()) {
            System.out.println("Hiển thị button Finish trên màn hình.");
        } else {
            System.out.println("Không tìm thấy button Finish!");
        }
        //Step 7: Click Finish
        finishButton.click();

        WebElement text1 = driver.findElement(By.xpath("//span[text()='Checkout: Complete!']"));
        text1.getText();
        System.out.println(text1.getText());

        WebElement text2 = driver.findElement(By.xpath("//h2[text()='Thank you for your order!']"));
        text2.getText();
        System.out.println(text2.getText());

        WebElement text3 = driver.findElement(By.xpath("//div[contains(text(), 'dispatch')]"));
        text3.getText();
        System.out.println(text3.getText());

        WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
        backHomeButton.isDisplayed();
    }
}
