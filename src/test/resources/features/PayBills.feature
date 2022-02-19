Feature: Pay Bills Page Contents

  Background:
    Given the user is logged in
    And Navigate to "Pay Bills" page

  Scenario: Pay Bills page title check
    Then the title should be "Zero - Pay Bills"


  Scenario Outline: Payment <condition>
    When User fills the form "<condition>"
    And User clicks the Pay button
    Then User gets "<textMessage>" message in "<messageField>"

    Examples:
      | condition          | textMessage                             | messageField      |
      | accordingly        | The payment was successfully submitted. | Success           |
      | without amount box | Please fill out this field.             | Amount Validation |
      | without date box   | Please fill out this field.             | Date Validation   |


  Scenario Outline: <field> should not accept <characterType>
    When User fills the form "<explanation>"
    Then "<field>" field should not accept "<characterType>"

    Examples:
      | explanation                                  | field  | characterType           |
      | with alphabetical characters in amount field | Amount | alphabetical characters |
      | with special characters in amount field      | Amount | special characters      |
      | with alphabetical characters in date field   | Date   | alphabetical characters |
      | with special characters in date field        | Date   | special characters      |

