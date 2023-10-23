Feature: SwagLabs Feature
  SwagLabs Web Testing

  Background: Access as standard-user
    Given Navigate to SwagLabs homepage
    And Insert username
      | username      |
      | standard_user |
    And Insert password
      | password     |
      | secret_sauce |
    And Click login

  Scenario: Verify login
    Then Verify url
    And Close SwagLabs window

  Scenario: Check first item and add it to shopping cart
    When Inspect first item
    And Add item to shopping cart
    And Navigate to shopping cart
    Then Check item name
    And Close SwagLabs window