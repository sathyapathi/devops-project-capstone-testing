package com.selenium.InsureMeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class InsureMeTest {
    public static void main(String[] args) {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();

        // Set Chrome options for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Runs Chrome in headless mode
        options.addArguments("--disable-gpu"); // Applicable to Windows to avoid issues
        options.addArguments("--window-size=1920,1080"); // Set a standard window size
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resources issues

        // Initialize WebDriver with options
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open the URL
            // driver.get("http://54.86.207.129:8080/contact.html");
            driver.get("http://172.31.81.101:8080/contact.html");


            // Fill the form
            driver.findElement(By.id("inputName")).sendKeys("NewName");
            driver.findElement(By.id("inputNumber")).sendKeys("9999999999");
            driver.findElement(By.id("inputMail")).sendKeys("new@abc.com");
            driver.findElement(By.id("inputMessage")).sendKeys("New test message");

            // Click Submit Button
            driver.findElement(By.id("my-button")).click();

            // Wait for response message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement responseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("response")));

            // Capture response message
            String outputMessage = responseElement.getText();
            String expectedMessage = "Message Sent";

            // Validate Output
            if (outputMessage.equals(expectedMessage)) {
                System.out.println("✅ Test Case Passed! Expected: \"" + expectedMessage + "\", Actual: \"" + outputMessage + "\"");
            } else {
                System.out.println("❌ Test Case Failed! Expected: \"" + expectedMessage + "\", Actual: \"" + outputMessage + "\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

 
