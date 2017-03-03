Feature: Test various error scenarios on the calculator page

  Scenario: Don't enter any dates
    Given I navigate to the Calculator page
    And I click on 'Continue'
    Then I should see the Calculator page with errors
    And form error should be 'These dates are not valid'

  Scenario: End date is before Start date
    Given I navigate to the Calculator page
    And I set the date field startDate to 1 January 2018
    And I set the date field endDate to 1 November 2017
    And I click on 'Continue'
    Then I should see the Calculator page with errors
    And form error should be 'The end date must be later than the start date'