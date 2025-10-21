package com.vin.automation.pages;

import com.vin.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomersPage extends BasePage {
    private final By searchInput = By.xpath("//input[@ng-model='searchCustomer']");
    private final By firstRowName = By.cssSelector("table tbody tr:first-child td:nth-child(1)");

    public CustomersPage search(String text){
        type(searchInput, text);
        return this;
    }

    public String firstRowFirstName(){
        WebElement e = el(firstRowName);
        return e.getText();
    }
}
