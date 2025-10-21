package com.vin.automation.core.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestRetryListener extends TestListenerAdapter implements IRetryAnalyzer {
    private static final int MAX_RETRY = 1;
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            return true;
        }
        return false;
    }
}
