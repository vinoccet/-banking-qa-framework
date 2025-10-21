@api
Feature: Public API sample

  Scenario: Get user from ReqRes
    Given the public API "https://reqres.in"
    When I GET "/api/users/2"
    Then response status is 200
    And field "data.id" is not empty
