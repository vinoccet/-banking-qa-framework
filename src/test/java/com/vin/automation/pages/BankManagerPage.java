package com.vin.automation.pages;

import com.vin.automation.core.BasePage;
import org.openqa.selenium.By;

public class BankManagerPage extends BasePage {
    private final By addCustomerTab = By.xpath("//button[@ng-class='btnClass1']");
    private final By openAccountTab = By.xpath("//button[@ng-class='btnClass2']");
    private final By customersTab = By.xpath("//button[@ng-class='btnClass3']");

    public AddCustomerPage goToAddCustomer(){
        click(addCustomerTab);
        return new AddCustomerPage();
    }

    public OpenAccountPage goToOpenAccount(){
        click(openAccountTab);
        return new OpenAccountPage();
    }

    public CustomersPage goToCustomers(){
        click(customersTab);
        return new CustomersPage();
    }
}
