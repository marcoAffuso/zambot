Feature: Login
  Scenario: Failed login with incorrect credentials
    Given I open the login page "https://www.mailinator.com"
    When I enter username "goldrake76@mailinator.com"
    And I enter password "wrongpass"
    And I click the login button
    Then I verify an error message containing "Invalid credentials"
