Feature: Login Function

  Background:
    Given the user is on the login page

  Scenario Outline: Login with invalid credentials
    When the user enters wrong "<username>" or "<password>"
    Then the user get error message "Login and/or password are wrong."

    Examples:
      | username | password |
      | onur     | omer     |
      | username | recepabi |
      | erdemabi | password |
      |          |          |

  Scenario: Login with valid credentials
    When the user enters "username" and "password"
    Then "Account Summary" page should be displayed