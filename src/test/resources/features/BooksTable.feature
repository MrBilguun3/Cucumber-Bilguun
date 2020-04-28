Feature: books table


  Scenario: Verify search results
    Given I am on the login page
    And I login to application as a student
    When I go to "Books" page
    When I search for "Tales and adventures of Mr Ayasun"
    Then search table should contain results matching adventures of Mr Ayasun

  @wip
  Scenario: Verify search results
    Given I am on the login page
    And I login to application as a librarian
    And I navigate to "Books" page
    When I edit book The kite runner
    Then I verify book information
    | name            | author          | year |
    | The kite runner | Khaled Hosseini | 2003 |


