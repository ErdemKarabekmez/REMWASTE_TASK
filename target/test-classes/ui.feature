Feature: Creating, editing, and deleting blog posts (UI Tests)

  @scenario_6
  Scenario: User creates a new blog post successfully

    Given the user is logged in to the platform
    When the user clicks on the user icon at the top right corner
    And the user selects the New option from the dropdown
    And the user is redirected to the new blog page in the same tab
    And the user enters a title into the title input box
    And the user enters an image url into the image url input box
    And the user enters blog details into the detail input box
    And the user clicks on the add new blog button
    Then the new blog post should appear on the blog dashboard


  @scenario_7
  Scenario: User updates an existing blog post successfully

    Given the user is logged in to the platform
    When the user clicks on the user icon at the top right corner
    And the user selects the New option from the dropdown
    And the user is redirected to the new blog page in the same tab
    And the user enters a title into the title input box
    And the user enters an image url into the image url input box
    And the user enters blog details into the detail input box
    And the user clicks on the add new blog button
    Then the new blog post should appear on the blog dashboard
    When the user clicks on the last blog post
    And the user clicks on the update button
    And the user updates the title field
    And the user clicks update blog button
    And the user should see a success update message

  @scenario_8
  Scenario: User deletes an existing blog post successfully

    Given the user is logged in to the platform
    When the user clicks on the user icon at the top right corner
    And the user selects the New option from the dropdown
    And the user is redirected to the new blog page in the same tab
    And the user enters a title into the title input box
    And the user enters an image url into the image url input box
    And the user enters blog details into the detail input box
    And the user clicks on the add new blog button
    Then the new blog post should appear on the blog dashboard
    When the user clicks on the last blog post
    And the user clicks on the delete button
    And the user should see a success delete message







