Feature: Account summary page contents

  Background:
    Given the user is logged in
    Then "Account Summary" page should be displayed


  Scenario: Account Summary page title and Account types check
    Then the title should be "Zero - Account Summary"
    Then the the following account types should be seen
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

  Scenario: Credit Account table check
    Then Credit Accounts table must have following columns
      | Account     |
      | Credit Card |
      | Balance     |