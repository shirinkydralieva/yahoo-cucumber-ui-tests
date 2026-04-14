Feature: Yahoo Mail Workflow

  Background:
    Given I am logged in to Yahoo Mail

  @regression
  Scenario: Save email as draft
    Given I start composing a new email
    When I enter email recipient
    And I enter email subject
    And I enter email body
    And I close the email editor
    Then the email is saved in Drafts

  @regression
  Scenario: Send email from drafts
    Given I have a draft with recipient, subject and body filled
    When I send the email
    Then the email appears in Sent folder

  @smoke
  Scenario Outline: Prevent sending with invalid email
    Given I start composing a new email
    When I enter "<invalid_email>" as recipient
    And I send the email
    Then I see an error message about invalid email address

    Examples:
      | invalid_email    |
      | plainaddress     |
      | @missinguser.com |
      | user@.com        |