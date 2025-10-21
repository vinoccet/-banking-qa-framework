@ui
Feature: Add customers from JSON

  Background:
    Given I open the Banking app
    And I navigate to Bank Manager > Add Customer

  Scenario: Load data from JSON and add all
    When I add customers from json file "data/customers.json"
    Then I should see "Ivy" present in Customers list
