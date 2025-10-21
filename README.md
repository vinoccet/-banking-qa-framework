# Banking QA Framework (Java 21)

**Stack**: Selenium ${selenium.version}, Cucumber ${cucumber.version} (TestNG), TestNG ${testng.version}, Allure ${allure.version}, RestAssured ${restassured.version}  
**AUT**: https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login

## Quick Start
```bash
# Java 21 and Maven installed
mvn -v

# Run UI only
mvn clean test -Pui -Dheadless=true -Dbrowser=chrome

# Run API only
mvn clean test -Papi

# Run both
mvn clean test -Pall

# Generate Allure report
mvn allure:report
# Or serve interactively (requires Allure CLI installed)
# allure serve target/allure-results
```

## Key Design
- **ThreadLocal WebDriver**: `DriverManager` keeps an isolated WebDriver per thread.
- **Driver Factory (Factory + Singleton-ish Session per thread)**: `DriverFactory` creates drivers based on `-Dbrowser`.
- **Page Object Model**: `BasePage` with explicit waits; pages live under `com.vin.automation.pages`.
- **BaseTest**: Handles driver lifecycle + failure screenshots (also used from Cucumber Hooks).
- **Retry Analyzer**: `TestRetryListener` retries once on failure.
- **DI**: Cucumber `cucumber-picocontainer` seeds a shared `World` object.
- **Reporting**: Allure via `allure-cucumber7-jvm` + `allure-testng`.
- **API**: `ApiClient` and `public_api.feature` show RestAssured usage.

## Useful System Props
- `-Dbrowser=chrome|firefox|edge`
- `-Dheadless=true|false`
- `-DbaseUrl=...`

## Notes
- Uses Selenium Manager, so no manual driver binaries needed.
- Demo scenarios:
  - Add customer, open account, verify listing.
  - Deposit & withdraw as existing demo user.
  - Sample Rest API GET using reqres.in.
