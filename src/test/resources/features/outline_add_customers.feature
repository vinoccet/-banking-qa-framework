@ui
Feature: Add customers via Scenario Outline

  Background:
    Given I open the Banking app

  @smoke
  Scenario Outline: Add one customer and open account
    When as Bank Manager I add customer "<firstName>" "<lastName>" with postcode "<postCode>"
    And I open an account for the customer in currency "<currency>"
    Then customer should be searchable by first name "<firstName>"

    Examples:
      | firstName | lastName | postCode | currency |
      | Dana      | Cruz     | 600001   | Dollar   |
      | Evan      | Zhang    | 641001   | Pound    |
      | Freya     | Kaur     | 110045   | Rupee    |
