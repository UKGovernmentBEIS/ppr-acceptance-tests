@Report @ServerValidation
Feature: Test server validations


  Scenario: Validate the error messages when a blank form is submitted
    When I start to prepare a report for The Testing Company
    And I click on 'Continue'
    Then I should see the following errors for the fields:
      | reportDates.startDate                              | This date is not valid      |
      | reportDates.endDate                                | This date is not valid      |
      | paymentHistory.averageDaysToPay                    | Please enter a whole number |
      | paymentHistory.percentageSplit.percentWithin30Days | Please enter a whole number |
      | paymentHistory.percentageSplit.percentWithin60Days | Please enter a whole number |
      | paymentHistory.percentageSplit.percentBeyond60Days | Please enter a whole number |
      | paymentHistory.percentPaidBeyondAgreedTerms        | Please enter a whole number |
      | paymentTerms.terms                                 | Please answer this question |
      | paymentTerms.paymentPeriod                         | Please enter a whole number |
      | paymentTerms.maximumContractPeriod                 | Please enter a whole number |
      | paymentTerms.paymentTermsChanged.changed.yesNo     | Please answer this question |
      | paymentTerms.disputeResolution                     | Please answer this question |
      | offerEInvoicing                                    | Please answer this question |
      | offerSupplyChainFinancing                          | Please answer this question |
      | retentionChargesInPolicy                           | Please answer this question |
      | retentionChargesInPast                             | Please answer this question |
      | paymentCodes.yesNo                                 | Please answer this question |
    And there should be no error for these fields:
      | paymentTerms.maximumContractPeriodComment       |
      | paymentTerms.paymentTermsComment                |
      | paymentTerms.paymentTermsChanged.changed.text   |
      | paymentTerms.paymentTermsChanged.notified.yesNo |
      | paymentTerms.paymentTermsChanged.notified.text  |
