package com.vin.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void set(WebDriver driver) {
        TL.set(driver);
    }

    public static WebDriver get() {
        return TL.get();
    }

    public static void unload() {
        TL.get().quit();

        TL.remove();
    }
}
