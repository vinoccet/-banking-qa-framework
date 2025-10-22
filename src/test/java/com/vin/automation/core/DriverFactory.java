package com.vin.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {
    private DriverFactory(){}

    public static RemoteWebDriver newDriver(String browser, boolean headless){
        return switch (browser.toLowerCase()){
            case "chrome" -> createChrome(headless);
            case "firefox" -> createFirefox(headless);
            case "edge" -> createEdge(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static RemoteWebDriver createChrome(boolean headless){
        ChromeOptions opts = new ChromeOptions();
        if (System.getenv("HEADFUL")==null) {
            opts.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage");
        }
        return new ChromeDriver(opts);
    }

    private static RemoteWebDriver createFirefox(boolean headless){
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("-headless");
        return new RemoteWebDriver(options);
    }

    private static RemoteWebDriver createEdge(boolean headless){
        EdgeOptions options = new EdgeOptions();
        if (headless) options.addArguments("--headless=new");
        return new RemoteWebDriver(options);
    }
}
