@smoke @lib-132 @navigation
Feature: Page navigation links

  # login as a librarian
  # click on th users link
  # verify page Users

  # login as a librarian
  # click on th Books link
  # verify page Books

  Background:
    Given I am on the login page
    And I login as a librarian

  @db
  Scenario: Go to users page
    When I click on "Users" link
    Then "Users" page should be displayed


  Scenario: Go to books page
    When I click on "Books" link
    Then "Books" page should be displayed


  Scenario: Go to dashboard page
    And I click on "Books" link
    When I click on "Dashboard" link
    Then "Dashboard" page should be displayed