package com.vin.automation.core;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        WebDriver driver = DriverFactory.newDriver(Config.browser(), Config.headless());
        driver.manage().window().maximize();
        DriverManager.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){
        if (!result.isSuccess() && Config.screenshotOnFailure()){
            takeScreenshot("Failure Screenshot");
        }
        DriverManager.unload();
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] takeScreenshot(String name){
        return ((TakesScreenshot) DriverManager.get()).getScreenshotAs(OutputType.BYTES);
    }
}
