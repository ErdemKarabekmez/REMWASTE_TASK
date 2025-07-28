package com.remwaste.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {

            switch (ConfigurationReader.get("browser")) {
                case "chrome":
                    driverPool.set(new ChromeDriver());
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

    private Driver()  {

    }

    public static void closeDriver() {

        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
