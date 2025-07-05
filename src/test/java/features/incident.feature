Feature: Validate the POST, GET methods of the incident table

  Scenario: User should able to get all records from the incident table
    Given user set base uri "https://dev265761.service-now.com" of the service now instance
    And user set the base path "/api/now/table/{tableName}" of the service now table api
    And user set the path parameter for "tableName" key as "incident" value
    And user set the basic authencation username as "admin" and password as "d@9IvhOh5DR*"
    When user hit the get http method
    Then user recived the succesfully response

  Scenario: User should able to get a single record from the incident table
    Given user set base uri "https://dev265761.service-now.com" of the service now instance
    And user set the base path "/api/now/table/{tableName}/{sys_id}" of the service now table api
    And user set the path parameter for "tableName" key as "incident" value
    And user set the path parameter for "sys_id" key as "ffef35afc391221082c2feac0501312b" value
    And user set the basic authencation username as "admin" and password as "d@9IvhOh5DR*"
    When user hit the get http method
    Then user recived the succesfully response

  Scenario: User should able to get a single record from the incident table using data table
    Given user set base uri "https://dev265761.service-now.com" of the service now instance
    And user set the base path "/api/now/table/{tableName}/{sys_id}" of the service now table api
    And user set path parameters key and value
      | tableName | incident                         |
      | sys_id    | ffef35afc391221082c2feac0501312b |
    And user set the basic authencation username as "admin" and password as "d@9IvhOh5DR*"
    When user hit the get http method
    Then user recived the success response with following detail
      | 200 | OK | application/json | ffef35afc391221082c2feac0501312b |
