package com.vin.automation.core;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends BaseTest {

    @Before
    public void beforeScenario(){
        super.setUp();
    }

    @After
    public void afterScenario(io.cucumber.java.Scenario scenario){
        if (scenario.isFailed() && Config.screenshotOnFailure()) {
            attachScreenshot("Failed step");
        }
        DriverManager.unload();
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] attachScreenshot(String name){
        return ((TakesScreenshot) DriverManager.get()).getScreenshotAs(OutputType.BYTES);
    }
}
