Feature: Tag management

  @createTag
  Scenario: Create a new tag
    Given the user is on the TagResource endpoint
    When the user adds a new tag
    Then the response should contain the newly created tag

  @deleteTag
  Scenario: Delete the created tag
    Given the user is on the TagResource endpoint
    When the user adds a new tag
    And the user deletes the newly created tag
    Then the tag should be deleted from the system

  @GetTagById
  Scenario: Get a tag by its ID
    Given the user is on the TagResource endpoint
    When the user requests a tag by its ID
    Then the response should contain the tag with the specified ID


  @updateTag
  Scenario: Update a tag
    Given the user is on the TagResource endpoint
    When the user adds a new tag
    Then the response should contain the newly created tag
    When the user updates the tag
    Then the response should contain the updated tag

  @getAllTags
  Scenario: Get All Tags
    Given the user is on the TagResource endpoint
    When the user requests all tags
    Then the response should have a status code of 200 OK
    And the response should contain a non-empty list of tags

  @createTagWithInvalidData
  Scenario: Create a Tag With Invalid Data
    Given the user is on the TagResource endpoint
    When the user tries to create a new tag with invalid data
    Then the response should indicate a validation error

  @createTag
  Scenario: Create a new tag with an existing name
    Given the user is on the TagResource endpoint
    When the user adds a new tag with the name "Nom Tag"
    Then the response should contain a conflict status
    And the response should contain an error message