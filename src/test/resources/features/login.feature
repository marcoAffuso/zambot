Feature: Login
  Scenario: Failed login with incorrect credentials
    Given I open the login page "https://example.com/login"
    When I enter username "wronguser"
    And I enter password "wrongpass"
    And I click the login button
    Then I verify an error message containing "Invalid credentials"