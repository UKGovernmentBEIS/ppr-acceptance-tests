Feature: Calculate reporting periods and filing deadlines

  Scenario: Display calculator page
    Given I navigate to the Calculator page
    When I set these date field values:
      | startDate | 1 January 2017   |
      | endDate   | 31 December 2017 |
    And I click on 'Continue'
    Then I should see the Answer page