@Questionnaire
Feature: Ask questions to see if user needs to publish reports in third year

  Scenario: In the third year flow 1
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I select 'No' and continue to the Company Balance Sheet Question year 3 page
    And I select 'No' and continue
    Then I should see the No need to report page
    And the message should be "You should check at the beginning of every financial year to see if you need to report."

  Scenario: In the third year flow 2
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I select 'Yes' and continue to the Company Balance Sheet Question year 3 page
    And I select 'Yes' and continue to the Subsidiaries Question page
    And I select 'No' and continue
    Then I should see the Need to report page
    And I should see a reason of "had a turnover of more than £36 million on its last 2 balance sheet dates"
    And I should see a reason of "had more than £18 million balance sheet total on its last 2 balance sheet dates"

  Scenario: In the third year flow 3
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I select 'Yes' and continue to the Company Balance Sheet Question year 3 page
    And I select 'No' and continue to the Company Employee Question year 3 page
    And I select 'Yes' and continue to the Subsidiaries Question page
    And I select 'No' and continue
    Then I should see the Need to report page
    And I should see a reason of "had a turnover of more than £36 million on its last 2 balance sheet dates"
    And I should see a reason of "had an average of more than 250 employees during both of its last 2 financial years"

