package com.vin.automation.steps;

import com.vin.automation.core.util.DataLoaders;
import com.vin.automation.model.CustomerData;
import com.vin.automation.pages.AddCustomerPage;
import com.vin.automation.pages.OpenAccountPage;
import com.vin.automation.pages.BankManagerPage;
import com.vin.automation.pages.CustomersPage;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class JsonSteps {
    @When("I add customers from json file {string}")
    public void add_customers_from_json(String path){
        List<CustomerData> rows = DataLoaders.fromJsonArray(path);
        new BankManagerPage().goToAddCustomer();
        for (CustomerData c : rows) {
            String full = c.firstName() + " " + c.lastName();
            String alert = new AddCustomerPage()
                    .enterFirstName(c.firstName())
                    .enterLastName(c.lastName())
                    .enterPostCode(c.postCode())
                    .submitAndGetAlert();
            Assert.assertTrue(alert.contains("Customer added successfully"));

        }
    }
}

