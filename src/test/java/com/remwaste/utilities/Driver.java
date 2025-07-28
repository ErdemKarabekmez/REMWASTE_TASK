package com.remwaste.utilities;


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

           /* switch (ConfigurationReader.get("browser")) {
                case "chrome":
                    driverPool.set(new ChromeDriver());
                    break;

            */

            switch (ConfigurationReader.get("browser")) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();

                    // Headless parametresi dışarıdan geliyorsa:
                    if ("true".equals(System.getProperty("headless"))) {
                        options.addArguments("--headless=new");
                    }

                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");

                    // Benzersiz user-data-dir (özellikle CI/CD için)
                    try {
                        Path tempProfile = Files.createTempDirectory("chrome-profile");
                        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());
                    } catch (Exception e) {
                        // Hata olursa logla, ama testleri durdurma
                        System.out.println("Temp chrome profile oluşturulamadı: " + e.getMessage());
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


            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        return driverPool.get();
    }

    private Driver() {

    }

    public static void closeDriver() {

        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
