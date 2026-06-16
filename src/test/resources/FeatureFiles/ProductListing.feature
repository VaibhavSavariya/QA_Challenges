Feature: Verify Product Listing and Pagination

  Background:
    Given user navigates to product listing page

  Scenario: Verify user can navigate between product pages
    When user clicks page number 2
    Then page number 2 should be active
    And products displayed should be different from page 1

    When user clicks Next button
    Then page number 3 should be active

    When user clicks Previous button
    Then page number 2 should be active

    And total products displayed should be 10

  Scenario: Verify category-wise product counts match expected data
  When user search for "Books"
  Then total books products displayed should be 10

  Scenario: Identify products by rating and price within each category
  When user search for "Clothing" it should displayed product with highest rating and highest price