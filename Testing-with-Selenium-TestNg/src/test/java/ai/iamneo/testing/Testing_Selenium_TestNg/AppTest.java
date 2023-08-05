package ai.iamneo.testing.Testing_Selenium_TestNg;

import org.testng.annotations.Test;
import java.net.URL;
import java.util.List;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class AppTest {
    WebDriver driver = null;
    String url = "https://www.facebook.com";
    ChromeOptions options = new ChromeOptions();

    @BeforeTest
    public void beforeTest() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/home/coder/project/workspace/chromedriver");
        driver = new RemoteWebDriver(new URL("http://localhost:8080"), options);
    }

    @Test
    public void facebookSignUpTest() {
        // Step 1: Open the Chrome browser and navigate to the URL
        driver.get(url);

        // Step 2: Verify that the page is redirected to "https://www.facebook.com"
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.facebook.com")) {
            System.out.println("Page redirected to https://www.facebook.com");
        } else {
            System.out.println("Page not redirected to https://www.facebook.com");
        }

        // Step 3: Verify that there is a "Create an account" section on the page.
        try {
            WebElement createAccountSection = driver.findElement(By.id("u_0_13")); // Assuming the ID is correct
            if (createAccountSection.isDisplayed()) {
                System.out.println("Create an account section is present on the page");
            } else {
                System.out.println("Create an account section is NOT present on the page");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Create an account section is NOT present on the page");
        }

        // Step 4: Fill in the text boxes: First Name, Surname, Mobile Number or email address,
        // "Re-enter mobile number", new password.
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Doe");
        driver.findElement(By.name("reg_email__")).sendKeys("johndoe@example.com");
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("johndoe@example.com");
        driver.findElement(By.name("reg_passwd__")).sendKeys("myPassword123");

        // Step 5: Update the date of birth in the drop-down.
        driver.findElement(By.name("birthday_day")).sendKeys("15");
        driver.findElement(By.name("birthday_month")).sendKeys("March");
        driver.findElement(By.name("birthday_year")).sendKeys("1990");

        // Step 6: Select gender.
        driver.findElement(By.name("sex")).click(); // Assuming there's a valid gender option with the name "sex"

        // Step 7: Click on "Create an account".
        driver.findElement(By.name("websubmit")).click();

        // Step 8: Verify that the account is created successfully.
        // Here, we will check for the presence of a success message element after the account is created.
        WebElement successMessageElement = driver.findElement(By.xpath("//div[contains(text(), 'Welcome to Facebook')]"));
        if (successMessageElement.isDisplayed()) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Failed to create an account.");
        }

        // Note: You may need to modify the locators above based on the actual HTML structure of the Facebook page.

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
