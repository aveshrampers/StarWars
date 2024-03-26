@ui
Feature: Verify Movie Information

  Scenario Outline:Sort movies by <Header> and assert the last movie in the list is ‘The Phantom Menace’
    Given I am on the movie listing page
    When I click the <Header> header
    Then the last movie in the list should be "The Phantom Menace"
    Examples:
      | Header    |
      | "Title"   |
      | "Episode" |

  Scenario:View the movie ‘The Empire Strikes Back’ and check if the ‘Species’ list has ‘Wookie’
    Given I am on the movie listing page
    When I navigate to the movie details page for "The Empire Strikes Back"
    Then I should see "Wookie" listed

  Scenario:Assert that ‘Planets’ ‘Camino’ is not part of the movie ‘The Phantom Menace’
    Given I am on the movie listing page
    When I navigate to the movie details page for "The Phantom Menace"
    Then I should not see the planet "Camino" listed