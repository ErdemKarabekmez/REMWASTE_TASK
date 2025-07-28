Feature: Login with valid credentials (UI Tests)

  @scenario_1
  Scenario: Successful login with valid email address and password

    Given the user navigates to the homepage
    When the user clicks on the user icon at the top right corner
    And the user selects the Login option from the dropdown
    And the user is redirected to the login page in the same tab
    And the user enters a valid email address and password and clicks the Login button
    And the user is redirected to the dashboard page in the same tab
    Then the user should see the name displayed at the top right corner

  @scenario_2
  Scenario: Unsuccessful login with invalid email address and invalid password

    Given the user navigates to the homepage
    When the user clicks on the user icon at the top right corner
    And the user selects the Login option from the dropdown
    And the user is redirected to the login page in the same tab
    And the user enters an invalid email address and invalid password and clicks the login button
    And the user should see an error message
    And the user should remain on the login page

  @scenario_3
  Scenario: Unsuccessful login with blank email address field and valid password

    Given the user navigates to the homepage
    When the user clicks on the user icon at the top right corner
    And the user selects the Login option from the dropdown
    And the user is redirected to the login page in the same tab
    And the user leaves the email address field blank and enters a valid password and clicks the login button
    And the user should see an error message
    And the user should remain on the login page

  @scenario_4
  Scenario: Unsuccessful login with valid email address and blank password field
    Given the user navigates to the homepage
    When the user clicks on the user icon at the top right corner
    And the user selects the Login option from the dropdown
    And the user is redirected to the login page in the same tab
    And the user enters a valid email address and leaves the password field blank and clicks the login button
    And the user should see an error message
    And the user should remain on the login page

  @scenario_5
  Scenario: Unsuccessful login with blank email address field and blank password field
    Given the user navigates to the homepage
    When the user clicks on the user icon at the top right corner
    And the user selects the Login option from the dropdown
    And the user is redirected to the login page in the same tab
    And the user leaves the email address field blank and leaves the password field blank and clicks the login button
    And the user should see an error message
    And the user should remain on the login page
