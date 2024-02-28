@crud-owner
Feature: Maintain Owners

	@list-owner
  Scenario: List all owners
    Given I visit the HOMEPAGE
    When I click on the OWNERS option
    And I click on the ALL option
      Then I should see a list of all owners is displayed

  @add-owner
  Scenario: Add owner
    Given I visit the HOMEPAGE
    When I click on the OWNERS option
    And I click on the ADD NEW option
    And I fill in "firstName" with "Juan"
    And I fill in "lastName" with "Perez"
    And I fill in "address" with "San Isidro"
    And I fill in "city" with "Lima"
    And I fill in "telephone" with "987654321"
    And I press Add Owner
    Then I should see a list of all owners is displayed
