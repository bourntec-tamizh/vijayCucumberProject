@RUN
@REGRESSION

Feature: This feature books the flight tickets
  Scenario: This test books tickets for one way journey
    When User open united home page
    And User selects Book tab
    And User selects flight type as "oneway"

  @SMOKE
  Scenario Outline: This test books tickets for two way journey for flight type "<FlightType>"
    When User open united home page
    And User selects Book tab
    And User selects flight type as "<FlightType>"
    Examples:
      | FlightType | FromCity |
      | Roundtrip  | Hyd      |
      | oneway  | Hyd      |