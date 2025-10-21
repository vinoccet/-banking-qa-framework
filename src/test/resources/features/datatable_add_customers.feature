@ui
Feature: Add customers via DataTable

  Background:
    Given I open the Banking app
    And I navigate to Bank Manager > Add Customer

  Scenario: Add multiple customers using a DataTable
    When I add customers:
      | firstName | lastName | postCode | currency |
      | Ana       | Stone    | 600001   | Dollar   |
      | Ben       | Ray      | 641001   | Pound    |
      | Cara      | Li       | 500032   | Rupee    |
    Then I should see "Ana" present in Customers list