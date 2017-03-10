@Report @JsValidation
Feature: Test javascript validations


  Scenario: Validate number field error messages
    When I start to prepare a report for The Testing Company
    Then the js validation should give these errors for the numeric fields:
      | paymentHistory.averageDaysToPay                    | -1  | Please enter 0 or greater                 |
      | paymentHistory.averageDaysToPay                    | 30  |                                           |
      | paymentHistory.averageDaysToPay                    | xx  | Please enter a whole number               |
      | paymentTerms.paymentPeriod                         | -1  | Please enter 0 or greater                 |
      | paymentTerms.paymentPeriod                         | 30  |                                           |
      | paymentTerms.paymentPeriod                         | xx  | Please enter a whole number               |
      | paymentTerms.maximumContractPeriod                 | -1  | Please enter 0 or greater                 |
      | paymentTerms.maximumContractPeriod                 | 30  |                                           |
      | paymentTerms.maximumContractPeriod                 | xx  | Please enter a whole number               |
      | paymentHistory.percentageSplit.percentWithin30Days | -1  | This should be a number between 0 and 100 |
      | paymentHistory.percentageSplit.percentWithin30Days | 101 | This should be a number between 0 and 100 |
      | paymentHistory.percentageSplit.percentWithin30Days | 30  |                                           |
      | paymentHistory.percentageSplit.percentWithin30Days | xx  | Please enter a whole number               |
      | paymentHistory.percentageSplit.percentWithin60Days | -1  | This should be a number between 0 and 100 |
      | paymentHistory.percentageSplit.percentWithin60Days | 101 | This should be a number between 0 and 100 |
      | paymentHistory.percentageSplit.percentWithin60Days | 30  |                                           |
      | paymentHistory.percentageSplit.percentWithin60Days | xx  | Please enter a whole number               |
      | paymentHistory.percentageSplit.percentBeyond60Days | -1  | This should be a number between 0 and 100 |
      | paymentHistory.percentageSplit.percentBeyond60Days | 101 | This should be a number between 0 and 100 |
      | paymentHistory.percentageSplit.percentBeyond60Days | 30  |                                           |
      | paymentHistory.percentageSplit.percentBeyond60Days | xx  | Please enter a whole number               |
      | paymentHistory.percentPaidBeyondAgreedTerms        | -1  | This should be a number between 0 and 100 |
      | paymentHistory.percentPaidBeyondAgreedTerms        | 101 | This should be a number between 0 and 100 |
      | paymentHistory.percentPaidBeyondAgreedTerms        | 30  |                                           |
      | paymentHistory.percentPaidBeyondAgreedTerms        | xx  | Please enter a whole number               |
