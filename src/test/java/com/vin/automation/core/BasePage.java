package com.vin.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriverWait webWait() {
        return new WebDriverWait(DriverManager.get(), Duration.ofSeconds(Config.explicitWaitSec()));
    }

    protected WebElement refresh(By locator) {
        return webWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    protected WebElement el(By locator) {
        return webWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        webWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        int attempts = 0;

        while(true) {
            try {
               WebElement e = refresh(locator);
                e.clear();
                e.sendKeys(text);
                break;
            } catch (StaleElementReferenceException exception) {
              if(++attempts>3) {
                  System.out.println("Stale element, retrying...");

              }
            }
        }
    }

    public String title() {
        return DriverManager.get().getTitle();
    }
}
