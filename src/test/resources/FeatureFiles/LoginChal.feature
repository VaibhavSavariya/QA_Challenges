@regression @login
Feature: Verify Login Functionality

  Background:
    Given user navigates to login challenge page

  @smoke
  Scenario: Verify login with valid Creds
    When user enters valid creds
    Then user should navigates to dashboard page

  Scenario: verify login with invalidCreds
    When user enters "admin" and "user123"
    Then user should see error message "Invalid username or password."

  Scenario: verify login with emptyFields
    When user enters "" and ""
    Then user should see error message "Both fields are required."