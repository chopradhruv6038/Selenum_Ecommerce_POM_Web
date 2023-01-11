package Org.Selenium.Factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {


    protected WebDriver driver;

    public WebDriver initializeDriver(String browser) {

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().cachePath("driver").setup();
                driver = new ChromeDriver();
                break;


            case "firefox":
                WebDriverManager.firefoxdriver().cachePath("driver").setup();
                driver = new FirefoxDriver();
                break;

            case "safari":
                WebDriverManager.safaridriver().cachePath("driver").setup();
                driver = new SafariDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().cachePath("driver").setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not found : " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        return driver;


    }
}
