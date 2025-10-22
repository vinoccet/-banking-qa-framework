package com.vin.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.vin.automation.steps", "com.vin.automation.core"},
        tags = "@smoke",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true
)
public class BankTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
