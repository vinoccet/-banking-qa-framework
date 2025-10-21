package com.vin.automation.pages;

import com.vin.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;

public class AddCustomerPage extends BasePage {
    private final By firstName = By.xpath("//input[@ng-model='fName']");
    private final By lastName  = By.xpath("//input[@ng-model='lName']");
    private final By postCode  = By.xpath("//input[@ng-model='postCd']");
    private final By addCustomerBtn = By.xpath("//button[text()='Add Customer']");

    public AddCustomerPage enterFirstName(String v){ type(firstName, v); return this; }
    public AddCustomerPage enterLastName(String v){ type(lastName, v); return this; }
    public AddCustomerPage enterPostCode(String v){ type(postCode, v); return this; }

    public String submitAndGetAlert(){
        click(addCustomerBtn);
        Alert a = com.vin.automation.core.DriverManager.get().switchTo().alert();
        String txt = a.getText();
        a.accept();
        return txt;
    }
}
