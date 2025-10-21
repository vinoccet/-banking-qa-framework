package com.vin.automation.pages;

import com.vin.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {
    private final By customerSelect = By.id("userSelect");
    private final By currencySelect = By.id("currency");
    private final By processBtn = By.xpath("//button[text()='Process']");

    public OpenAccountPage chooseCustomer(String fullName){
        new Select(el(customerSelect)).selectByVisibleText(fullName);
        return this;
    }

    public OpenAccountPage chooseCurrency(String currency){
        new Select(el(currencySelect)).selectByVisibleText(currency);
        return this;
    }

    public String processAndGetAlert(){
        click(processBtn);
        var alert = com.vin.automation.core.DriverManager.get().switchTo().alert();
        String msg = alert.getText();
        alert.accept();
        return msg;
    }
}
