@regression @productFilter
Feature: Verify Product Filtering and Search

  Background:
    Given user navigates to product filter page

  @smoke
  Scenario: Verify Category Filter
    When user selects "Sports" category
    Then all displayed products should belong to "Sports" category

  Scenario: Verify price range filter
    When user sets price range from 200 to 500
    Then all displayed products should have price between 200 and 500

  Scenario: Verify rating filter
    When user selects 3 star rating filter
    Then all displayed products should have rating greater than or equal to 3 stars

  Scenario: Verify inStock filter
    When user checks inStock checkbox
    Then all displayed products should show "In Stock" status

  Scenario: Verify Category, Price and Rating filter combination
    When user selects "Sports" category
    And user sets price range from 0 to 500
    And user checks inStock checkbox
    Then all displayed products should belong to "Sports" category price between 0 and 500 and have rating greater than or equal to 3 stars

  Scenario: Verify filters reset clears all applied filters
    Given user selects "Sports" category
    And user sets price range from 0 to 500
    When user clicks reset filters button
    Then all filters should be cleared
    And all products should be displayed