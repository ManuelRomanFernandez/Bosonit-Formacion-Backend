Feature: Google Feature
  Google Web Testing

  Scenario: Open Google and search "selenium"
    Given Navigate to Google homepage
    And Close configuration window
    When Introduce text to search input
    Then Check web title starts with text
    And Close Google window