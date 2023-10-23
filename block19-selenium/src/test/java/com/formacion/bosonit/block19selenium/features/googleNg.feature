Feature: Google Feature With NG
  Testing with NG and listeners

  Scenario: Open Google and search "selenium" NG version
    Given NG - Navigate to Google homepage
    And NG - Close configuration window
    When NG - Introduce text to search input
    Then NG - Check web title starts with text
    And NG - Close Google window