Feature: Calculate reporting periods and filing deadlines

  Scenario Outline: Calculate one period and deadline
    Given I navigate to the Calculator page
    When I set the date field startDate to <Start Date>
    And I set the date field endDate to <End Date>
    And I click on 'Continue'
    Then I should see the Answer page
    And it should show that the financial year runs from <Start Date> to <End Date>
    And I should see 1 calculated periods
    And Period 1 should run from <Period Start 1> to <Period End 1> with deadline <Deadline 1>

    Examples:
      | Start Date     | End Date          | Period Start 1 | Period End 1      | Deadline 1      |
      | 1 January 2018 | 30 September 2018 | 1 January 2018 | 30 September 2018 | 30 October 2018 |

  Scenario Outline: Calculate two periods and deadlines
    Given I navigate to the Calculator page
    When I set the date field startDate to <Start Date>
    And I set the date field endDate to <End Date>
    And I click on 'Continue'
    Then I should see the Answer page
    And it should show that the financial year runs from <Start Date> to <End Date>
    And if the <End Date> is before 6 April 2017 then I should see a message about that
    And I should see 2 calculated periods
    And Period 1 should run from <Period Start 1> to <Period End 1> with deadline <Deadline 1>
    And Period 2 should run from <Period Start 2> to <Period End 2> with deadline <Deadline 2>

    Examples:
      | Start Date       | End Date         | Period Start 1   | Period End 1     | Deadline 1        | Period Start 2   | Period End 2     | Deadline 2        |
      | 1 January 2017   | 31 December 2017 | 1 January 2018   | 30 June 2018     | 30 July 2018      | 1 July 2018      | 31 December 2018 | 30 January 2019   |
      | 1 January 2016   | 31 December 2016 | 1 January 2018   | 30 June 2018     | 30 July 2018      | 1 July 2018      | 31 December 2018 | 30 January 2019   |
      | 1 September 2017 | 31 August 2018   | 1 September 2017 | 28 February 2018 | 30 March 2018     | 1 March 2018     | 31 August 2018   | 30 September 2018 |
      | 1 January 2018   | 31 March 2019    | 1 January 2018   | 30 June 2018     | 30 July 2018      | 1 July 2018      | 31 March 2019    | 30 April 2019     |
      | 1 March 2018     | 28 February 2019 | 1 March 2018     | 31 August 2018   | 30 September 2018 | 1 September 2018 | 28 February 2019 | 30 March 2019     |


  Scenario Outline: Calculate three periods and deadlines
    Given I navigate to the Calculator page
    When I set the date field startDate to <Start Date>
    And I set the date field endDate to <End Date>
    And I click on 'Continue'
    Then I should see the Answer page
    And it should show that the financial year runs from <Start Date> to <End Date>
    And I should see 3 calculated periods
    And Period 1 should run from <Period Start 1> to <Period End 1> with deadline <Deadline 1>
    And Period 2 should run from <Period Start 2> to <Period End 2> with deadline <Deadline 2>
    And Period 3 should run from <Period Start 3> to <Period End 3> with deadline <Deadline 3>

    Examples:
      | Start Date     | End Date          | Period Start 1 | Period End 1     | Deadline 1    | Period Start 2   | Period End 2     | Deadline 2        | Period Start 3 | Period End 3      | Deadline 3        |
      | 1 January 2018 | 1 April 2019      | 1 January 2018 | 30 June 2018     | 30 July 2018  | 1 July 2018      | 31 December 2018 | 30 January 2019   | 1 January 2019 | 1 April 2019      | 1 May 2019        |
      | 1 January 2018 | 30 September 2019 | 1 January 2018 | 30 June 2018     | 30 July 2018  | 1 July 2018      | 31 December 2018 | 30 January 2019   | 1 January 2019 | 30 September 2019 | 30 October 2019   |
      | 1 January 2018 | 1 October 2019    | 1 January 2018 | 30 June 2018     | 30 July 2018  | 1 July 2018      | 31 December 2018 | 30 January 2019   | 1 January 2019 | 1 October 2019    | 31 October 2019   |
      | 31 August 2017 | 30 August 2019    | 31 August 2017 | 27 February 2018 | 29 March 2018 | 28 February 2018 | 30 August 2018   | 29 September 2018 | 31 August 2018 | 30 August 2019    | 29 September 2019 |
      | 28 August 2017 | 27 August 2019    | 28 August 2017 | 27 February 2018 | 29 March 2018 | 28 February 2018 | 27 August 2018   | 26 September 2018 | 28 August 2018 | 27 August 2019    | 26 September 2019 |


