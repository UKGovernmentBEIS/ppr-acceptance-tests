Feature: Ask questions to see if user needs to publish reports

  Scenario: Not a company or LLP
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'No'
    And I click on 'Continue'
    Then I should see the No need to report page

  Scenario: In the first year
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes'
    And I click on 'Continue'
    Then I should see the Financial Year Question page
    And I select 'First'
    And I click on 'Continue'
    Then I should see the No need to report page
    And the reason should be "This is because it’s in its first year of operations. You should check back at the beginning of your second financial year to see if you need to report."

