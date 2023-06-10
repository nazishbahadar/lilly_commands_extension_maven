package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WinAppDriverHandler {
    private static WebDriver driver = null;

    public static void startSession() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root"); // Root to have control over all windows

        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723"), capabilities); // WinAppDriver runs on this address and port by default
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // Implicit wait
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void stopSession() {
        driver.quit();
    }

    public static void clickElementByName(String elementName) {
        WebElement element = driver.findElement(By.name(elementName));
        element.click();
    }
}


