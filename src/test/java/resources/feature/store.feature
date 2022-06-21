Feature: Testing different requests on stores option of the best buy application
  Scenario:
    When I create a new store by providing the information name type address address_ city state zip lat lng hours
    And I verify that the sore has been created
    And I update a address of the store created
    And I verify that the store name hs been updated
    And I delete store has been deleted with same id