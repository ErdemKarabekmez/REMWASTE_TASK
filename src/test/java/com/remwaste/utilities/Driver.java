package com.remwaste.utilities;


import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Driver {

    /***
     This class manages browser driver instance.
     This class ensures that only one WebDriver instance is created and used throughout
     the test execution, providing methods to initialize, get, and quit the driver.
     It also supports browser type selection (e.g., Chrome, Firefox)
     based on configuration, allowing flexible browser switching.
     ***/
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {

            switch (ConfigurationReader.get("browser")) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");

                    try {
                        Path tempProfile = Files.createTempDirectory("chrome-profile-" + System.nanoTime());
                        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());
                    } catch (Exception e) {
                        System.out.println("Temp chrome profile could not created: " + e.getMessage());
                    }
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "edge":
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    driverPool.set(new SafariDriver());
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    break;
            }
        }

        return driverPool.get();
    }

    private Driver() {

    }

    @After
    public static void closeDriver() {

        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
