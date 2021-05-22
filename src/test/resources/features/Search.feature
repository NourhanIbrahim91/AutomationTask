Feature: Search for image Functionality
  In order to search for an image
  As a google user
  I want to view search results successfully

  Scenario: Search for image
    Given I am in the search page of google images
    When I enter "flower image"
    Then I should visit the specified result in the configuration file