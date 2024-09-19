Feature: Customer able to use Change Request API

  Scenario: Validate that user is able to create new Change Request in Service Now 
    Given Set a base URI and base path
    And Set Authentication for the create operation
    When User hit the post change request method with the request payload
    Then New change request record should be created
    
    
  Scenario: Validate that user is able to get all Change Request in Service Now with Query Params
    Given Set a base URI and base path
    And Set Authentication for the create operation
    When User hit the get change request method with the request payload and query params
    Then All change request record should be created with those query param


