@Calculator
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


  Scenario: Start date is invalid
    Given I navigate to the Calculator page
    And I set the number field startDate.day to '31'
    And I set the number field startDate.month to '2'
    And I set the number field startDate.year to '2017'
    And I click on 'Continue'
    Then I should see the Calculator page with errors
    And form error should be 'These dates are not valid'
    And field error for startDate should be 'This date is not valid'

  Scenario: End date is invalid
    Given I navigate to the Calculator page
    And I set the number field endDate.day to '31'
    And I set the number field endDate.month to '2'
    And I set the number field endDate.year to '2017'
    And I click on 'Continue'
    Then I should see the Calculator page with errors
    And form error should be 'These dates are not valid'
    And field error for endDate should be 'This date is not valid'