Feature: Ask questions to see if user needs to publish reports

  Scenario: Not a company or LLP
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'No'
    And I click on 'Continue'
    Then I should see the No need to report page