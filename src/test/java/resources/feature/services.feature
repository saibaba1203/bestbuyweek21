Feature: Testing different requests on services option of the best buy application
  Scenario Outline:
    When I create a new service by providing the information name "<name>"
    And I verify that the service has been created
    And I update a name of the service
    And I verify that the service has been updated
    And I delete the service with same id
    Then I verify that the service has been deleted


    Examples:
    |name|
    |Magnolia Home Theater|