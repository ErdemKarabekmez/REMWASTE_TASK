Feature: API Tests

  @scenario_9
  Scenario: User logs in successfully and receives a token
    Given the user sends a POST request to the login endpoint with credentials
    And the response status code should be 200
    Then the response body should contain an authentication token

  @scenario_10
  Scenario: User gets all booking IDs
    Given the user sends a GET request to the bookings endpoint
    And the response status code should be 200
    Then the response body should contain a list of booking IDs

  @scenario_11
  Scenario: User gets a booking by id
    Given the user sends a GET request by using id
    And the response status code should be 200
    Then the response body should contain first name and last name information

  @scenario_12
  Scenario: User creates a new booking
    Given the user sends a POST request with the following data
      | firstname       | Olivia     |
      | lastname        | Wilson     |
      | totalprice      | 444        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    And the response status code should be 200
    Then the response should contain the same booking details

  @scenario_13
  Scenario: User updates an existing booking
    Given the user updates an existing booking with the following data
      | firstname       | Olivia1    |
      | lastname        | Wilson1    |
      | totalprice      | 4441       |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    And the response status code should be 200
    Then the booking should updated

  @scenario_14
  Scenario: User deletes an existing booking
    Given the user deletes the existing booking
    Then Then the user should verify that the booking is successfully deleted

  @scenario_15
  Scenario: User tries to update an existing booking with invalid token (Negative Scenario)
    Given the user tries to update an existing booking with the following data with invalid token
      | firstname       | John       |
      | lastname        | Kary       |
      | totalprice      | 456        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    Then the response status code should be 403







