package com.vin.automation.steps;

import com.vin.automation.core.World;
import com.vin.automation.pages.BankManagerPage;
import com.vin.automation.pages.CustomerPage;
import com.vin.automation.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class UiSteps {

    private final World world;
    private final LoginPage loginPage = new LoginPage();

    public UiSteps(World world) {
        this.world = world;
    }

    @Given("I open the Banking app")
    public void i_open_app() {
        loginPage.open();
    }

    @When("as Bank Manager I add customer {string} {string} with postcode {string}")
    public void add_customer(String first, String last, String post) {
        var mgr = loginPage.clickBankManager();
        var add = mgr.goToAddCustomer();
        String alert = add.enterFirstName(first).enterLastName(last).enterPostCode(post).submitAndGetAlert();
        world.lastCreatedCustomerFullName = String.format("%s %s", first, last);
        Assert.assertTrue(alert.contains("Customer added successfully"), "Unexpected alert: " + alert);
    }

    @And("I open an account for the customer in currency {string}")
    public void open_account(String currency) {
        var mgr = new BankManagerPage();
        var open = mgr.goToOpenAccount();
        String msg = open.chooseCustomer(world.lastCreatedCustomerFullName).chooseCurrency(currency).processAndGetAlert();
        Assert.assertTrue(msg.contains("Account created successfully"), "Unexpected alert: " + msg);
    }

    @Then("customer should be searchable by first name {string}")
    public void verify_customer_in_list(String firstName) {
        var mgr = new BankManagerPage();
        var customers = mgr.goToCustomers();
        customers.search(firstName);
        Assert.assertEquals(customers.firstRowFirstName(), firstName);
    }

    @When("as Customer {string} I deposit {string}")
    public void customer_deposit(String fullName, String amount) {
        var customerPage = loginPage.clickCustomer();
        customerPage.chooseUser(fullName).login().deposit(amount);
        Assert.assertTrue(customerPage.status().contains("Deposit Successful"));
    }

    @Then("I can withdraw {string} and see {string}")
    public void withdraw_and_verify(String amount, String expected) throws InterruptedException {
        var customerPage = new CustomerPage();
        customerPage.withdraw(amount);
        Assert.assertTrue(customerPage.status().contains(expected), "Status was: " + customerPage.status());
    }


    @When("as Customer I deposit for following")
    public void asCustomerIDepositForFollowing(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        dataTable.forEach(dataMap -> {
            String fullName = dataMap.get("Name");
            String amount = dataMap.get("Amount");
            var customerPage = loginPage.clickCustomer();
            customerPage.chooseUser(fullName).login().deposit(amount);
            Assert.assertTrue(customerPage.status().contains("Deposit Successful"));

        });
    }
}
