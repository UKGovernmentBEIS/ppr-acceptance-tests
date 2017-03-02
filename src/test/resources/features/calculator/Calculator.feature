Feature: Calculate reporting periods and filing deadlines

  Scenario Outline: Calculate two periods and deadlines
    Given I navigate to the Calculator page
    When I set the date field startDate to <Start Date>
    And I set the date field endDate to <End Date>
    And I click on 'Continue'
    Then I should see the Answer page
    And I should see 2 calculated periods
    And Period 1 should run from <Period Start 1> to <Period End 1> with deadline <Deadline 1>
    And Period 2 should run from <Period Start 2> to <Period End 2> with deadline <Deadline 2>

    Examples:
      | Start Date       | End Date         | Period Start 1   | Period End 1     | Deadline 1    | Period Start 2 | Period End 2     | Deadline 2        |
      | 1 January 2017   | 31 December 2017 | 1 January 2018   | 30 June 2018     | 30 July 2018  | 1 July 2018    | 31 December 2018 | 30 January 2019   |
      | 1 January 2016   | 31 December 2016 | 1 January 2018   | 30 June 2018     | 30 July 2018  | 1 July 2018    | 31 December 2018 | 30 January 2019   |
      | 1 September 2017 | 31 August 2018   | 1 September 2017 | 28 February 2018 | 30 March 2018 | 1 March 2018   | 31 August 2018   | 30 September 2018 |


  Scenario: Don't enter any dates
    Given I navigate to the Calculator page
    And I click on 'Continue'
    Then I should see the Calculator page with errors