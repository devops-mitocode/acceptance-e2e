@crud-owner
Feature: Maintain Owners

	@list-owner
  Scenario: List all owners
    Given I visit the HOMEPAGE
    When I click on the OWNERS option
    And I click on the ALL option
		Then I should see a list of all owners is displayed
