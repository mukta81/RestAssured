Feature: Customer able to use JIRA Issues API

 Scenario: Validate that user is able to create new Issue using Jira API Services
  Given Set a base URI and base path
  And Set Authentication for the create operation
  When User hit the POST method with the request payload
  Then New Issue should be created
  
 Scenario: Validate that user is able to create new Issue with attachment using Jira API Services
  Given Set a base URI and base path
  And Set Authentication for the create operation
  When User hit the POST method with the request payload and attachment
  Then New Issue should be created  with attachment
    
 Scenario: Validate that user is able to get all Issues from a project using Jira API Services
  Given Set a base URI and base path
  And Set Authentication for the create operation
  When User hit the GET method with the request payload
  Then All Issue in a project should be returned
 