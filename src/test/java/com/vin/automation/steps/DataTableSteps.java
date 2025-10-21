package com.vin.automation.steps;

import com.vin.automation.model.CustomerData;
import com.vin.automation.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.List;

public class DataTableSteps {
    private BankManagerPage manager;
    private LoginPage loginPage;

    @And("I navigate to Bank Manager > Add Customer")
    public void goToAddCustomer() {
        loginPage = new LoginPage();
        manager = loginPage.clickBankManager();
        manager.goToAddCustomer();
    }

    @When("I add customers:")
    public void i_add_customers(DataTable table) {
        List<CustomerData> rows = table.asMaps().stream()
                .map(m -> new CustomerData(m.get("firstName"), m.get("lastName"), m.get("postCode"), m.get("currency")))
                .toList();

        for (CustomerData c : rows) {
            String full = c.firstName() + " " + c.lastName();
            String alert = new AddCustomerPage()
                    .enterFirstName(c.firstName())
                    .enterLastName(c.lastName())
                    .enterPostCode(c.postCode())
                    .submitAndGetAlert();
            Assert.assertTrue(alert.contains("Customer added successfully"));

            String msg = new OpenAccountPage()
                    .chooseCustomer(full)
                    .chooseCurrency(c.currency())
                    .processAndGetAlert();
            Assert.assertTrue(msg.contains("Account created successfully"));
        }
    }

    @Then("I should see {string} present in Customers list")
    public void i_should_see_present(String first) {
        String name = manager.goToCustomers().search(first).firstRowFirstName();
        Assert.assertEquals(name, first);
    }
}

