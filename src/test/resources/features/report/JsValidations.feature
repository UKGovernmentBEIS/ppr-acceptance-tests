@Report @JsValidation
Feature: Test javascript validations

  Scenario: Validate "Average Time To Pay" field
    When I start to prepare a report for The Testing Company
    And I click on 'paymentHistory.averageDaysToPay'
    And I set the number field paymentHistory.averageDaysToPay to '-1'
    And I click on 'paymentHistory.percentageSplit.percentWithin30Days'
    Then the error for paymentHistory.averageDaysToPay should be 'Please enter 0 or greater'
