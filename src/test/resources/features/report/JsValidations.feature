@Report @JsValidation
Feature: Test javascript validations


  Scenario: Validate number field error messages
    When I start to prepare a report for The Testing Company
    Then the js validation should give these errors for the numeric fields:
      | paymentHistory.averageDaysToPay | -1 | Please enter 0 or greater   |
      | paymentHistory.averageDaysToPay | xx | Please enter a whole number |
      | paymentHistory.averageDaysToPay | 30 |                             |
