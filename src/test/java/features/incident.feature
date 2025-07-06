Feature: Validate the POST, GET methods of the incident table

  Background: 
    Given user set base uri "https://dev265761.service-now.com" of the service now instance
    And user set the basic authencation username as "admin" and password as "d@9IvhOh5DR*"

  @smoke @e2e
  Scenario: User should able to get all records from the incident table
    Given user set the base path "/api/now/table/{tableName}" of the service now table api
    And user set the path parameter for "tableName" key as "incident" value
    When user hit the get http method
    Then user recived the succesfully response
  @smoke
  Scenario: User should able to get a single record from the incident table
    Given user set the base path "/api/now/table/{tableName}/{sys_id}" of the service now table api
    And user set the path parameter for "tableName" key as "incident" value
    And user set the path parameter for "sys_id" key as "ffef35afc391221082c2feac0501312b" value
    When user hit the get http method
    Then user recived the succesfully response
  @smoke
  Scenario: User should able to get a single record from the incident table using data table
    Given user set the base path "/api/now/table/{tableName}/{sys_id}" of the service now table api
    And user set path parameters key and value
      | tableName | incident                         |
      | sys_id    | ffef35afc391221082c2feac0501312b |
    When user hit the get http method
    Then user recived the success response with following detail
      | statusCode  | 200                              |
      | statusLine  | OK                               |
      | contentType | application/json                 |
      | sysId       | ffef35afc391221082c2feac0501312b |
  @smoke
  Scenario: User should able to create a single incident record
    Given user set the base path "/api/now/table/{tableName}" of the service now table api
    And user set the path parameter for "tableName" key as "incident" value
    And user set the "Content-Type" as a key and "application/json" as value in the header
    When user set the description "Create new record using POST method" in the request body
    And user set the short description "REST API" in the request body
    And user hit the post http method
    Then user finds in a single record in the incident table
  @e2e
  Scenario Outline: User should able to creates multiple incident records
    Given user set the base path "/api/now/table/{tableName}" of the service now table api
    And user set the path parameter for "tableName" key as "incident" value
    And user set the "Content-Type" as a key and "application/json" as value in the header
    When user set the description "<description>" in the request body
    And user set the short description "<shortDescription>" in the request body
    And user hit the post http method
    Then user able to create the mulitple records successfully

    Examples: 
      | description                          | shortDescription |
      | Create new record using POST method1 | REST API1        |
      | Create new record using POST method2 | REST API2        |
