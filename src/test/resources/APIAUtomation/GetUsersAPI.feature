@API
Feature: Get users data

  Scenario: Get Users Data
    When I submit GET request for usersAPI
    Then I verify status code is "200"