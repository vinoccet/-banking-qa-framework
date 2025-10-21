package com.vin.automation.pages;

import com.vin.automation.core.BasePage;
import com.vin.automation.core.DriverManager;
import com.vin.automation.core.Config;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By customerLoginBtn = By.xpath("//button[text()='Customer Login']");
    private final By bankManagerLoginBtn = By.xpath("//button[text()='Bank Manager Login']");

    public LoginPage open(){
        DriverManager.get().get(Config.baseUrl());
        return this;
    }

    public BankManagerPage clickBankManager(){
        click(bankManagerLoginBtn);
        return new BankManagerPage();
    }

    public CustomerPage clickCustomer(){
        click(customerLoginBtn);
        return new CustomerPage();
    }
}
