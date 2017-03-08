@Questionnaire @Year3
Feature: Ask questions to see if user needs to publish reports in second year

  Scenario Outline: No need to report without subsidiaries
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I give the answers <Company Answers>
    Then I should see the No need to report page
    And the message should be "You should check at the beginning of every financial year to see if you need to report."

    Examples: No subsidiaries
      | Company Answers |
      | No No           |
      | No Yes No       |

  Scenario Outline: No need to report with subsidiaries
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I give the answers <Company Answers>
    Then I should see the Subsidiaries Question page
    And I select 'Yes' and continue
    And I give the answers <Subsidiary Answers>
    Then I should see the No need to report page
    And the message should be "You should check at the beginning of every financial year to see if you need to report."

    Examples: Subsidiaries
      | Company Answers | Subsidiary Answers |
      | Yes Yes         | No No              |
      | Yes No Yes      | No No              |
      | Yes Yes         | Yes No No          |
      | Yes No Yes      | Yes No No          |


  Scenario Outline: Need to report without subsidiaries
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I give the answers <Company Answers>
    Then I should see the Subsidiaries Question page
    And I select 'No' and continue
    Then I should see the Need to report page
    And I should see the reasons <Reasons>

    Examples: No subsidiaries
      | Company Answers | Reasons                                  |
      | Yes Yes         | company.turnover.y3 company.balance.y3   |
      | Yes No Yes      | company.turnover.y3 company.employees.y3 |
      | No Yes Yes      | company.balance.y3  company.employees.y3 |

  Scenario Outline: Need to report with subsidiaries
    Given I navigate to the Questionnaire Start Page page
    And I click on 'Start now'
    Then I should see the Company or LLP Question page
    And I select 'Yes' and continue to the Financial Year Question page
    And I select 'ThirdOrLater' and continue to the Company Turnover Question year 3 page
    And I give the answers <Company Answers>
    Then I should see the Subsidiaries Question page
    And I select 'Yes' and continue
    And I give the answers <Subsidiary Answers>
    Then I should see the Need to report page
    And I should see the reasons <Reasons>

    Examples: With subsidiaries
      | Company Answers | Subsidiary Answers | Reasons                                                                       |
      | Yes Yes         | Yes Yes            | company.turnover.y3 company.balance.y3 group.turnover.y3 group.balance.y3     |
      | Yes No Yes      | Yes No Yes         | company.turnover.y3 company.employees.y3 group.turnover.y3 group.employees.y3 |
      | No Yes Yes      | No Yes Yes         | company.balance.y3 company.employees.y3 group.balance.y3 group.employees.y3   |


