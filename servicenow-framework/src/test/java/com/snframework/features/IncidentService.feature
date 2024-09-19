Feature: Customer able to use Incident API

Scenario: Validate that user is able to create new Incident using Incident Services
    Given Set a base URI and base path
    And Set Authentication for the create operation
    When User hit the post method with the request payload
    Then New Incident record should be created
  
Scenario Outline: Validate that user is able to create multiple new Incident using Incident Services
		Given Set a base URI and base path
    And Set Authentication for the create operation
    When User hit the post method with the multiple data payload "<short_description>","<description>"
    Then New Incident records should be created with following "<short_description>","<description>" details
    
 Examples: 
      | short_description | description                   | 
      | cucumber1         | description1 for the cucumber | 
      | cucumber2         | description2 for the cucumber | 