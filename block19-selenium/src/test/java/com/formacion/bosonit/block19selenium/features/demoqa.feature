Feature: DemoQA Feature
  DemoQA Web Testing

  Background: Access to DemoQA web
    Given Navigate to DemoQA homepage

  Scenario: Test combo-box web element
    When Access to widgets section
    And Click on Select Menu
    And Select first box third option
    Then Check option value
    And Close TootsQA window