package com.vin.automation.core;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public final class DriverFactory {
    private DriverFactory(){}

    public static WebDriver newDriver(String browser, boolean headless){
        return switch (browser.toLowerCase()){
            case "chrome" -> createChrome(headless);
            case "firefox" -> createFirefox(headless);
            case "edge" -> createEdge(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static RemoteWebDriver createChrome(boolean headless){
        ChromeOptions opts = new ChromeOptions();
        if (headless) {
            opts.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage");
        }
        return new ChromeDriver(opts);
    }

    private static RemoteWebDriver createFirefox(boolean headless){
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("-headless");
        return new RemoteWebDriver(options);
    }

    private static WebDriver createEdge(boolean headless) {
        var edge_driver_path = System.getProperty("user.dir")+"/driver/edgedriver_win64/msedgedriver.exe";
        System.setProperty("webdriver.edge.driver",edge_driver_path);
        EdgeOptions options = new EdgeOptions();
        if (headless) options.addArguments("--headless=new");
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        capabilities.setCapability("browserName", "Chrome");
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("browserVersion", "120.0");
        bstackOptions.put("userName", "vinothcts_BDWU69");
        bstackOptions.put("accessKey", "5pEKiQ5ezEAzhyyVz9nU");
        bstackOptions.put("consoleLogs", "info");
        capabilities.setCapability("bstack:options", bstackOptions);
        String browserstackUrl="https://" + Config.browserstackUsername() + ":" + Config.browserstackKey() + "@hub-cloud.browserstack.com/wd/hub";
        try {
            return new RemoteWebDriver(new URL(browserstackUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
