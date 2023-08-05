package ai.iamneo.testing.Testing_Selenium_TestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookSignUpTest {

    public static void main(String[] args) {
		WebDriver driver = null;
	    ChromeOptions options = new ChromeOptions();
        // Set the path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/home/coder/project/workspace/chromedriver");
		driver = new RemoteWebDriver(new URL("http://localhost:8080"), options);

        

        // Navigate to the Facebook Sign Up page
        driver.get("http://www.fb.com");

        // Verify if the page is redirected to "http://www.facebook.com"
        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals("http://www.facebook.com")) {
            System.out.println("Page redirected to Facebook Sign Up page successfully.");
        } else {
            System.out.println("Page redirection failed.");
        }

        // Verify that there is a "Create an account" section on the page
        WebElement createAccountSection = driver.findElement(By.id("signup_form"));
        if (createAccountSection.isDisplayed()) {
            System.out.println("Create an account section found on the page.");
        } else {
            System.out.println("Create an account section not found.");
        }

        // Fill in the text boxes
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Doe");
        driver.findElement(By.name("reg_email__")).sendKeys("example@example.com");
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("example@example.com");
        driver.findElement(By.name("reg_passwd__")).sendKeys("P@ssw0rd");

        // Update the date of birth in the drop-down
        WebElement dayDropdown = driver.findElement(By.id("day"));
        WebElement monthDropdown = driver.findElement(By.id("month"));
        WebElement yearDropdown = driver.findElement(By.id("year"));

        // You can update the date of birth according to your requirement
        dayDropdown.sendKeys("10");
        monthDropdown.sendKeys("Jan");
        yearDropdown.sendKeys("1990");

        // Select gender
        driver.findElement(By.cssSelector("input[name='sex'][value='2']")).click(); // Assuming '2' is the value for Female

        // Click on "Create an account"
        driver.findElement(By.name("websubmit")).click();

        // Wait for the account to be created successfully (You might need to implement wait mechanisms here)
        // For simplicity, let's wait for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the account is created successfully
        // For the purpose of this example, let's check if the user is redirected to the home page
        if (driver.getCurrentUrl().equals("https://www.facebook.com/")) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account creation failed.");
        }

        // Close the browser
        driver.quit();
    }
}

