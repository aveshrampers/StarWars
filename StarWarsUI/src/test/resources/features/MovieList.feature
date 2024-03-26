@api
Feature: Get the list of movies.

  Scenario: Get the list of movies and check if the movies count is 6
    Given the url
    Then assert response count is 6