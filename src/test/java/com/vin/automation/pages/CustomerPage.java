package com.vin.automation.pages;

import com.vin.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CustomerPage extends BasePage {
    private final By userSelect = By.id("userSelect");
    private final By loginBtn = By.xpath("//button[text()='Login']");
    private final By depositTab = By.xpath("//button[@ng-click='deposit()']");
    private final By withdrawlTab = By.xpath("//button[@ng-click='withdrawl()']");
    private final By amountInput = By.xpath("//input[@ng-model='amount']");
    private final By submitBtn = By.xpath("//button[@type='submit']");
    private final By message = By.cssSelector(".error"); // app uses .error div for status

    public CustomerPage chooseUser(String fullName){
        new Select(el(userSelect)).selectByVisibleText(fullName);
        return this;
    }
    public CustomerPage login(){ click(loginBtn); return this; }
    public CustomerPage deposit(String amount){
        click(depositTab);
        type(amountInput, amount);
        click(submitBtn);
        return this;
    }
    public CustomerPage withdraw(String amount) throws InterruptedException {
        click(withdrawlTab);
//        Thread.sleep(10000);
        type(amountInput, amount);
        click(submitBtn);
        return this;
    }
    public String status(){ return el(message).getText(); }
}
