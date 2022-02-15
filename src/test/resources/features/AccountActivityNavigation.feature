Feature: Navigating to specific accounts in Accounts Activity

  Background:
    Given the user is logged in

  Scenario Outline: Accounts redirect <link>
    When the user clicks on "<link>" link on the "<page>" page
    Then the "<nextPage>" page should be displayed
    And Account drop down should have "<accountDropdown>" selected

    Examples:
      | link        | page            | nextPage         | accountDropdown |
      | Savings     | Account Summary | Account Activity | Savings         |
      | Brokerage   | Account Summary | Account Activity | Brokerage       |
      | Checking    | Account Summary | Account Activity | Checking        |
      | Credit Card | Account Summary | Account Activity | Credit Card     |
      | Loan        | Account Summary | Account Activity | Loan            |

  Scenario: Account Activity page title check
    When Navigate to "Account Activity" page
    Then the title should be "Zero - Account Activity"

  Scenario: Account drop down default option
    When Navigate to "Account Activity" page
    Then Account drop down should have "Savings" selected

  Scenario: Account drop down check
    When Navigate to "Account Activity" page
    Then Account drop down should have the following options
      | Savings     |
      | Checking    |
      | Loan        |
      | Credit Card |
      | Brokerage   |

  Scenario: Transactions table check
    When Navigate to "Account Activity" page
    Then Transactions table should have following column names
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |




