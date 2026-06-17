@regression @shadowLogin
Feature: Verify ShadowRoot Login

  Background:
    Given user navigates to Shadow Login challenge page

  @smoke
  Scenario: Verify login with valid Creds
    When user enters username "admin" and password "admin123"
    Then user should see to dashboard page