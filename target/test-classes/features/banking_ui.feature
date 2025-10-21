@ui @smoketest
Feature: Banking flows - GlobalSQA demo

  Background:
    Given I open the Banking app

  Scenario: Create customer and open account
    When as Bank Manager I add customer "vinoth" "Kumar" with postcode "641001"
    And I open an account for the customer in currency "Dollar"
    Then customer should be searchable by first name "Vinoth"


  @transaction
  Scenario: Customer deposit and withdraw
    When as Customer "Harry Potter" I deposit "1000"
    Then I can withdraw "500" and see "Transaction successful"
