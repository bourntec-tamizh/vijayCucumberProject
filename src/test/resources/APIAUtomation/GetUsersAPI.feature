
Feature: Get users data

  Scenario: Get Users Data
    When I submit GET request for usersAPI
    Then I verify status code is "200"

#  @API
  Scenario Outline: Get Individual User Data for userid "<userid>"
    When I submit GET request for usersAPI for user "<userid>"
    Then I verify status code is "<statuscode>"
    And User verify response values
    Examples:
    |userid|statuscode|
    |  2    |      200    |
    |  3    |      200    |
    |  4    |      200    |

  @API
  Scenario Outline: Get Individual User Data for listusers for page "<page>"
    When I submit GET request for usersAPI for list of users in page "<page>"
    Then I verify status code is "<statuscode>"
    And User verify response values for user list
    Examples:
      |page|statuscode|
      |  2    |      200    |
